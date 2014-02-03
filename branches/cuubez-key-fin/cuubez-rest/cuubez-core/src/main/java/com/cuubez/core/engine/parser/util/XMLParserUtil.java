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
package com.cuubez.core.engine.parser.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParserUtil {

    public static List<Element> getElements(String tagName, Document doc) {

        List<Element> elements = new ArrayList<Element>();

        if (tagName == null) {
            return elements;
        }

        NodeList nList = doc.getElementsByTagName(tagName);

        if (nList == null) {
            return elements;
        }


        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) nNode;
                elements.add(element);
            }
        }

        return elements;

    }


    public static String getElementContent(Element element) throws TransformerException {

        javax.xml.transform.Source domSource = new DOMSource(element);
        StringWriter sw = new StringWriter();
        javax.xml.transform.stream.StreamResult streamResult = new StreamResult(sw);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        javax.xml.transform.Transformer identityTransform = transformerFactory.newTransformer();
        identityTransform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        identityTransform.transform(domSource, streamResult);

        return sw.toString();
    }

    public static Document createDocument(InputStream xmlStream) throws ParserConfigurationException, IOException {

        javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = null;

        try {

            document = db.parse(xmlStream);

        } catch (SAXException e) {
            //Do nothing
        }

        return document;
    }


    public static String getDocumentAsString(Document doc) throws TransformerFactoryConfigurationError, TransformerException {

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);

        return result.getWriter().toString();
    }


    public static void appendXmlFragment(DocumentBuilder docBuilder,
                                         Element parent, String fragment) throws IOException, SAXException {

        if (docBuilder != null && parent != null && fragment != null) {

            Document doc = parent.getOwnerDocument();
            Node fragmentNode = docBuilder.parse(
                    new InputSource(new StringReader(fragment)))
                    .getDocumentElement();
            fragmentNode = doc.importNode(fragmentNode, true);
            parent.appendChild(fragmentNode);

        }

    }


    public static Element getDirectChild(Element parent) {

        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {

            if (child instanceof Element) return (Element) child;
        }

        return null;
    }

}
