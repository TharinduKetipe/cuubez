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

package com.cuubez.core.security.sign;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.security.utils.PropertyResolver;
import com.cuubez.core.security.utils.StringOutputStream;
import com.cuubez.core.security.utils.resolver.OfflineResolver;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.FileInputStream;
import java.io.StringReader;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;


public class DocumentSignProcessor {

    static {
        org.apache.xml.security.Init.init();
    }


   	public void sign(MessageContext msgContext) throws Exception {

           ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");

           String keystoreType = PropertyResolver.getProperty("keystoreType");
           String keystoreFile = PropertyResolver.getProperty("keystoreFileForSign");
           String keystorePass = PropertyResolver.getProperty("keystorePass");
           String privateKeyAlias = PropertyResolver.getProperty("privateKeyAlias");
           String privateKeyPass = PropertyResolver.getProperty("privateKeyPass");
           String certificateAlias = PropertyResolver.getProperty("certificateAlias");

           KeyStore ks = KeyStore.getInstance(keystoreType);
           FileInputStream fis = new FileInputStream(keystoreFile);
           System.out.println("sign key file:"+keystoreFile);

           //load the keystore
           ks.load(fis, keystorePass.toCharArray());

           //get the private key for signing.
           PrivateKey privateKey = (PrivateKey) ks.getKey(privateKeyAlias, privateKeyPass.toCharArray());
           javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();

           //XML Signature needs to be namespace aware
           dbf.setNamespaceAware(true);

           Document doc = createDocument(msgContext.getContent());

           Element root = doc.getDocumentElement();

           //Create an XML Signature object from the document, BaseURI and
           //signature algorithm
           XMLSignature sig = new XMLSignature(doc, null, XMLSignature.ALGO_ID_SIGNATURE_DSA);


           /*Append the signature element to the root element before signing because
           this is going to be an enveloped signature.
           This means the signature is going to be enveloped by the document.
           Two other possible forms are enveloping where the document is inside the
           signature and detached where they are seperate.
           that they can be mixed in 1 signature with seperate references as
           shown below.*/

           root.appendChild(sig.getElement());

           sig.getSignedInfo().addResourceResolver(new OfflineResolver());

           {
               //create the transforms object for the Document/Reference
               Transforms transforms = new Transforms(doc);
            // make transformations
               transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
               transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);

               /*First we have to strip away the signature element (it's not part of the
               signature calculations). The enveloped transform can be used for this.
               Part of the signature element needs to be canonicalized. It is a kind
               of normalizing algorithm for XML. For more information please take a
               look at the W3C XML Digital Signature webpage.
               Add the above Document/Reference*/
               sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);

           }



           {
               //Add in the KeyInfo for the certificate that we used the private key of
               X509Certificate cert = (X509Certificate) ks.getCertificate(certificateAlias);

               sig.addKeyInfo(cert);
               sig.addKeyInfo(cert.getPublicKey());
               sig.sign(privateKey);
           }

           StringOutputStream stream = new StringOutputStream();
           XMLUtils.outputDOMc14nWithComments(doc, stream);

           msgContext.setContent(stream.toString());
           stream.close();
       }

   	public void verifySignature(MessageContext msgContext) throws Exception {

   	}


   	private static Document createDocument(String documentContent) throws Exception {
        javax.xml.parsers.DocumentBuilderFactory dbf =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new InputSource(new StringReader(documentContent)));
        return document;
    }

}
