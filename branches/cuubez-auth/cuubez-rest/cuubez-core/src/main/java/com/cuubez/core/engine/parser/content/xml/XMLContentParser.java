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
package com.cuubez.core.engine.parser.content.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.context.MediaType;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.engine.parser.content.ContentParser;

public class XMLContentParser implements ContentParser {
    private static Log log = LogFactory.getLog(XMLContentParser.class);
    private static final String BODY_ELEMENT = "Body";


    public void parse(ConfigurationContext configurationContext) throws CuubezException {

        Object returnObject = configurationContext.getMessageContext().getReturnObject();
        String xmlContent = null;

        try {

            xmlContent = prepareContent(Marshaller.marshal(returnObject));

        } catch (ParserConfigurationException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (IOException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (SAXException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (TransformerFactoryConfigurationError e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (TransformerException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        }
        configurationContext.getMessageContext().setContent(xmlContent);
        configurationContext.getMessageContext().setMediaType(MediaType.XML);

    }

    private String prepareContent(String xmlContent) throws ParserConfigurationException, IOException, SAXException, TransformerFactoryConfigurationError, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(BODY_ELEMENT);
        doc.appendChild(rootElement);
        appendXmlFragment(docBuilder, rootElement, xmlContent);

        return getDocumentAsString(doc);
    }


    private void appendXmlFragment(DocumentBuilder docBuilder,
                                   Element parent, String fragment) throws IOException, SAXException {

        if (fragment != null) {

            Document doc = parent.getOwnerDocument();
            Node fragmentNode = docBuilder.parse(
                    new InputSource(new StringReader(fragment)))
                    .getDocumentElement();
            fragmentNode = doc.importNode(fragmentNode, true);
            parent.appendChild(fragmentNode);

        }

    }

    private String getDocumentAsString(Document doc) throws TransformerFactoryConfigurationError, TransformerException {

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);

        return result.getWriter().toString();
    }


}
