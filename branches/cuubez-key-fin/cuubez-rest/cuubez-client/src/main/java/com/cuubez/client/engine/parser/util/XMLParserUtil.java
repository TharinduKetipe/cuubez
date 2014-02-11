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
package com.cuubez.client.engine.parser.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLParserUtil {
	
	public static List<Element> getElements(String tagName, Document doc) {
    	
        List<Element> elements = new ArrayList<Element>();
        
        if(tagName == null) {
        	return elements;
        }
        
    	NodeList nList = doc.getElementsByTagName(tagName);
    	
    	if(nList == null) {
    		return elements;
    	}
 
    	
		for (int temp = 0; temp < nList.getLength(); temp++) {
 
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
		      Element element = (Element) nNode;
		      elements.add(getDirectChild(element));  
		   }
		}
    
		return elements;

     }
	
public static List<Element> getElements(String tagName, Element rootElement) {
    	
        List<Element> elements = new ArrayList<Element>();
        
        if(tagName == null) {
        	return elements;
        }
        
    	NodeList nList = rootElement.getElementsByTagName(tagName);
    	
    	if(nList == null) {
    		return elements;
    	}
 
    	
		for (int temp = 0; temp < nList.getLength(); temp++) {
 
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
		      Element element = (Element) nNode;
		      elements.add(getDirectChild(element));  
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
	
	public static Element getDirectChild(Element parent) {
	      
		for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
	          
			if(child instanceof Element) return (Element) child;
	    }
	   
		return null;
	}
	
	public static Document createDocument(InputStream xmlStream) throws ParserConfigurationException, SAXException, IOException  {
        
	 javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
   	 javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
   	 Document document = db.parse(xmlStream);
   	 return document;

   }

}
