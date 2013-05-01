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
package com.cuubez.client.engine.parser.url.xml;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cuubez.client.context.MessageContext;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.engine.parser.url.URLParser;

public class XMLURLParser implements URLParser{
	
	private static String ROOT_ELEMENT = "parameters";
	private static String CHILD_ELEMENT = "parameter";

	@Override
	public void parse(MessageContext messageContext) throws CuubezException  {
		
		Object[] parameters = messageContext.getRequestContext().getParameters();
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		
		try {
			
				docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement(ROOT_ELEMENT);
				doc.appendChild(rootElement);
		
				if(parameters != null && parameters.length > 0) {
		
					for(Object parameter : parameters) {
						Element childElement = doc.createElement(CHILD_ELEMENT);
						rootElement.appendChild(childElement);
						String xmlFramgment = Marshaller.marshal(parameter);
						appendXmlFragment(docBuilder, childElement, xmlFramgment);
					}	
			
					
		
				}
				
				String parametersXml = getDocumentAsString(doc);
				messageContext.getRequestContext().setTransformedParameter(parametersXml);
				
			} catch (TransformerFactoryConfigurationError e) {
				throw new CuubezException(e);
			} catch (TransformerException e) {
				throw new CuubezException(e);
			} catch (IOException e) {
				throw new CuubezException(e);
			} catch (SAXException e) {
				throw new CuubezException(e);
			} catch (ParserConfigurationException e) {
				throw new CuubezException(e);
			}
			
		
	}
	
	
	private void appendXmlFragment(DocumentBuilder docBuilder,
			Element parent, String fragment) throws IOException, SAXException {
		
		Document doc = parent.getOwnerDocument();
		Node fragmentNode = docBuilder.parse(
				new InputSource(new StringReader(fragment)))
				.getDocumentElement();
		fragmentNode = doc.importNode(fragmentNode, true);
		parent.appendChild(fragmentNode);
		
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
