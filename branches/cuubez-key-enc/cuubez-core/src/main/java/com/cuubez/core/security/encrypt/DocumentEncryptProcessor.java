/**
 *	Copyright [2013] [www.cuubez.com]
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package com.cuubez.core.security.encrypt;

import com.cuubez.core.annotation.context.FieldAnnotationMetaData;
import com.cuubez.core.annotation.context.FieldMetaData;
import com.cuubez.core.context.MessageContext;
import com.cuubez.core.security.context.SecurityContext;
import com.cuubez.core.security.utils.PropertyResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.utils.EncryptionConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class DocumentEncryptProcessor {

    private static Log log = LogFactory.getLog(DocumentEncryptProcessor.class);

    static {
        org.apache.xml.security.Init.init();
    }

	public void encrypt(MessageContext msgContext, SecurityContext securityContext,boolean isFullDocument) throws Exception {


        Document document = createDocument(msgContext.getContent());
        SecretKey symmetricKey = GenerateDataEncryptionKey();
        SecretKey encSymmetricKey = loadKeyEncryptionKey();
        String algorithmURI = XMLCipher.TRIPLEDES_KeyWrap;


        XMLCipher keyCipher =
                XMLCipher.getInstance(algorithmURI);
        keyCipher.init(XMLCipher.WRAP_MODE, encSymmetricKey);
        EncryptedKey encryptedKey =
                keyCipher.encryptKey(document, symmetricKey);


        algorithmURI = XMLCipher.AES_128;

        XMLCipher xmlCipher =
                XMLCipher.getInstance(algorithmURI);
        xmlCipher.init(XMLCipher.ENCRYPT_MODE, symmetricKey);

        /*
         * Setting keyinfo inside the encrypted data being prepared.
         */
        EncryptedData encryptedData = xmlCipher.getEncryptedData();
        KeyInfo keyInfo = new KeyInfo(document);
        keyInfo.add(encryptedKey);
        encryptedData.setKeyInfo(keyInfo);

        if (isFullDocument) {

            document = encryptDocument(document, xmlCipher);

        } else {

            document = encryptTags(securityContext, document, xmlCipher);
        }
       /*
        * Output the document containing the encrypted information into
        * a file.
        */
        processDocument(document, msgContext);
    }



	public void encryptSignature(MessageContext msgContext, SecretKey key) throws Exception {

		Document document = createDocument(msgContext.getContent());
        SecretKey symmetricKey = GenerateDataEncryptionKey();
        SecretKey encSymmetricKey = loadKeyEncryptionKey();

        String algorithmURI = XMLCipher.TRIPLEDES_KeyWrap;
        XMLCipher keyCipher = XMLCipher.getInstance(algorithmURI);
        keyCipher.init(XMLCipher.WRAP_MODE, encSymmetricKey);
        EncryptedKey encryptedKey = keyCipher.encryptKey(document, symmetricKey);

        algorithmURI = XMLCipher.AES_128;

        XMLCipher xmlCipher = XMLCipher.getInstance(algorithmURI);
        xmlCipher.init(XMLCipher.ENCRYPT_MODE, symmetricKey);

        /*
         * Setting keyinfo inside the encrypted data being prepared.
         */
        EncryptedData encryptedData = xmlCipher.getEncryptedData();
        KeyInfo keyInfo = new KeyInfo(document);
        keyInfo.add(encryptedKey);
        encryptedData.setKeyInfo(keyInfo);

        List<Element> signElements = getElements("ds:Signature", document);

        for(Element signElement : signElements) {
        	xmlCipher.doFinal(document, signElement, true);

        }

        /*
         * Output the document containing the encrypted information into
         * a file.
         */
        processDocument(document, msgContext);
    }

    public void decrypt(MessageContext msgContext, SecurityContext securityContext, SecretKey key) throws Exception {

        Document document = loadEncryptionDocument(msgContext.getContent());

        List<Element> encryptedDataElements = getElements(document.getElementsByTagNameNS(EncryptionConstants.EncryptionSpecNS,
                EncryptionConstants._TAG_ENCRYPTEDDATA));

        /*
               * Load the key to be used for decrypting the xml data
               * encryption key.
               */
        SecretKey kek = loadKeyEncryptionKey();

        //String providerName = "BC";

        XMLCipher xmlCipher =
                XMLCipher.getInstance();
        /*
               * The key to be used for decrypting xml data would be obtained
               * from the keyinfo of the EncrypteData using the kek.
               */
        xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
        xmlCipher.setKEK(kek);
        /*
               * The following doFinal call replaces the encrypted data with
               * decrypted contents in the document.
               */

        for(Element encryptedDataElement : encryptedDataElements) {
            xmlCipher.doFinal(document, encryptedDataElement);
        }

        processDocument(document, msgContext);

    }


	private Document encryptTags(SecurityContext securityContext, Document document,
			XMLCipher xmlCipher) throws Exception {

		List<Element> elements = null;
		Set<String> returnObjects = securityContext.getTagsToEncrypt().keySet();

		if (returnObjects != null) {

			for (String returnObject : returnObjects) {

				elements = getElements(returnObject, document);

				if (elements != null) {

                    FieldAnnotationMetaData fieldAnnotationMetaData = securityContext.findTagsToEncrypt(returnObject);

					for (Element element : elements) {

						if (fieldAnnotationMetaData != null && fieldAnnotationMetaData.getFieldMetaData() != null) {

							for (FieldMetaData fieldMetaData : fieldAnnotationMetaData.getFieldMetaData()) {

								Element childElement = getDirectChild(element, fieldMetaData.getFieldName());

                                if(childElement != null) {
								    xmlCipher.doFinal(document, childElement, true);
                                }

							}
						}

					}
				}
			}
		}

		return document;

	}

	private Document encryptDocument(Document document, XMLCipher xmlCipher) throws Exception {

		Element rootElements = document.getDocumentElement();

		if (rootElements != null) {
			xmlCipher.doFinal(document, rootElements, true);
		}

		return document;
	}


    private static Document createDocument(String documentContent) throws Exception {
         javax.xml.parsers.DocumentBuilderFactory dbf =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
    	 javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
    	 Document document = db.parse(new InputSource(new StringReader(documentContent)));
    	 return document;
    }

    private static SecretKey loadKeyEncryptionKey() throws Exception {

      /*  File kekFile = new File(PropertyResolver.getProperty("keystoreFile")); ///Should get it from configuration file
        FileInputStream inputStream = new FileInputStream(kekFile);
        ObjectInputStream objectInputStream = new ObjectInputStream( inputStream );
        SecretKey secretKey = (SecretKey)objectInputStream.readObject ();
        System.out.println("Key encryption key stored in " + kekFile.toURI().toURL().toString());

        return secretKey; */

        return generateAndStoreKeyEncryptionKey();
    }

    private static SecretKey generateAndStoreKeyEncryptionKey() throws Exception {

        String jceAlgorithmName = "DESede";
        KeyGenerator keyGenerator =
                KeyGenerator.getInstance(jceAlgorithmName);
        SecretKey kek = keyGenerator.generateKey();

        byte[] keyBytes = kek.getEncoded();
        File kekFile = new File(PropertyResolver.getProperty("keystoreFile")); ///Should get it from configuration file
        FileOutputStream f = new FileOutputStream(kekFile);
        f.write(keyBytes);
        f.close();
        return kek;
    }

    private static SecretKey GenerateDataEncryptionKey() throws Exception {
        String jceAlgorithmName = "AES";
        KeyGenerator keyGenerator =
            KeyGenerator.getInstance(jceAlgorithmName);
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    private static void processDocument(Document doc, MessageContext msgContext) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);
        transformer.transform(source, result);
        msgContext.setContent(stringWriter.getBuffer().toString());

    }


    public List<Element> getElements(String tagName, Document doc) {

        List<Element> elements = new ArrayList<Element>();

        if(tagName == null) {
        	return elements;
        }

    	NodeList nList = doc.getElementsByTagName(tagName);


		for (int temp = 0; temp < nList.getLength(); temp++) {

		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		      Element element = (Element) nNode;
		       elements.add(element);
		   }
		}

     return elements;

     }


    public Element getDirectChild(Element parent, String name)
    {
        for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling())
        {
            if(child instanceof Element){

                if(name.equals(child.getNodeName())) {
                    return (Element) child;
                }

            } else {
                return parent;
            }
        }
        return null;
    }

    private static Document loadEncryptionDocument(String xmlContext) throws Exception {

        javax.xml.parsers.DocumentBuilderFactory dbf =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new InputSource(new StringReader(xmlContext)));
        System.out.println("Encryption document loaded");
        return document;
    }

    public static List<Element> getElements(NodeList nList) {

        List<Element> elements = new ArrayList<Element>();


        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) nNode;
                elements.add(element);
            }
        }
        return elements;
    }
}
