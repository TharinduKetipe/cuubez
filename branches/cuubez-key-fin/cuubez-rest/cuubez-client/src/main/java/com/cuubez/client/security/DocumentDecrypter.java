package com.cuubez.client.security;

import java.io.StringReader;
import java.io.StringWriter;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.utils.EncryptionConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cuubez.key.Utils;

public class DocumentDecrypter {

	static {
		org.apache.xml.security.Init.init();
	}

	public static Document decrypt(Document document) throws Exception {

		List<Element> encryptedDataElements = getElements(document
				.getElementsByTagNameNS(EncryptionConstants.EncryptionSpecNS,
						EncryptionConstants._TAG_ENCRYPTEDDATA));

		/*
		 * Load the key to be used for decrypting the xml data encryption key.
		 */
		Key kek = loadKeyEncryptionKey();
		System.out.println("Key -Enc-Key In Hex ="
				+ Utils.toHex(kek.getEncoded()));

		XMLCipher xmlCipher = XMLCipher.getInstance();

		xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
		xmlCipher.setKEK(kek);

		for (Element encryptedDataElement : encryptedDataElements) {
			xmlCipher.doFinal(document, encryptedDataElement);
		}

		processDocument(document);
		return document;

	}

	public static String decryptSignature(String msgContext) throws Exception {

		Document document = loadEncryptionDocument(msgContext);

		List<Element> encryptedDataElements = new ArrayList<Element>();
		List<Element> signElements = getElements("ds:Signature", document);

		for (Element element : signElements) {
			encryptedDataElements.addAll(getElements(element
					.getElementsByTagNameNS(
							EncryptionConstants.EncryptionSpecNS,
							EncryptionConstants._TAG_ENCRYPTEDDATA)));
		}

		Key kek = loadKeyEncryptionKey();

		XMLCipher xmlCipher = XMLCipher.getInstance();
		/*
		 * The key to be used for decrypting xml data would be obtained from the
		 * keyinfo of the EncrypteData using the kek.
		 */
		xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
		xmlCipher.setKEK(kek);

		/*
		 * The following doFinal call replaces the encrypted data with decrypted
		 * contents in the document.
		 */

		for (Element encryptedDataElement : encryptedDataElements) {
			xmlCipher.doFinal(document, encryptedDataElement);
		}

		return processDocument(document);

	}

	private static Document loadEncryptionDocument(String xmlContext)
			throws Exception {

		javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory
				.newInstance();
		dbf.setNamespaceAware(true);
		javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new InputSource(new StringReader(
				xmlContext)));
		System.out.println("Encryption document loaded");
		return document;
	}

	private static SecretKey loadKeyEncryptionKey() throws Exception {

		return ClientKeyRepositoryService.getInstance()
				.retrieveSecretSharedKeyForPrincipal("X");
	}

	private static String processDocument(Document doc) throws Exception {

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		DOMSource source = new DOMSource(doc);
		StringWriter stringWriter = new StringWriter();
		StreamResult result = new StreamResult(stringWriter);
		transformer.transform(source, result);
		return stringWriter.getBuffer().toString();

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

	public static List<Element> getElements(String tagName, Document doc) {

		List<Element> elements = new ArrayList<Element>();
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

	public static Element getDirectChild(Element parent, String name) {
		for (Node child = parent.getFirstChild(); child != null; child = child
				.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName()))
				return (Element) child;
		}
		return null;
	}

}
