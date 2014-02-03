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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.context.MessageContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.engine.parser.content.ContentParser;
import com.cuubez.core.engine.parser.util.XMLParserUtil;

public class XMLExceptionParser implements ContentParser {

    private static final String EXCEPTION = "Exception";

    @Override
    public void parse(ConfigurationContext configurationContext) throws CuubezException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(EXCEPTION);
            doc.appendChild(rootElement);
            int exceptionCode = configurationContext.getExceptionContext().getExceptionCode();
            XMLParserUtil.appendXmlFragment(docBuilder, rootElement, "<Code>" + exceptionCode + "</Code>");

            if (configurationContext.getMessageContext() == null) {
                MessageContext messageContext = new MessageContext();
                configurationContext.setMessageContext(messageContext);
            }

            configurationContext.getMessageContext().setContent(XMLParserUtil.getDocumentAsString(doc));

        } catch (IOException e) {
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (SAXException e) {
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (ParserConfigurationException e) {
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (TransformerFactoryConfigurationError e) {
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (TransformerException e) {
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
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
