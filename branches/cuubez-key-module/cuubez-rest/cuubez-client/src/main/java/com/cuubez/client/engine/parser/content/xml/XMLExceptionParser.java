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
package com.cuubez.client.engine.parser.content.xml;

import java.util.List;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cuubez.client.context.ExceptionContext;
import com.cuubez.client.context.MessageContext;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.engine.parser.content.ContentParser;
import com.cuubez.client.engine.parser.util.XMLParserUtil;

public class XMLExceptionParser implements ContentParser {
	
	private static final String EXCEPTION_ELEMENT = "Exception";
	private static final String EXCEPTION_CODE = "Code";

	@Override
	public void parse(MessageContext messageContext) throws CuubezException {
		
		try {

				Document doc = messageContext.getRequestContext().getDocument();
		
				if(doc != null) {
					
		    		List<Element> exceptionElements = XMLParserUtil.getElements(EXCEPTION_ELEMENT, doc);
					
					if(exceptionElements != null && exceptionElements.size() > 0) {
						
						for(Element element : exceptionElements) {
							
							if(EXCEPTION_CODE.equals(element.getNodeName())) {
								String code = element.getTextContent();
								populateException(code, messageContext);
								
							}
						}
						
					}
                    
				}
			
			} catch (TransformerException e) {
				throw new CuubezException(e);
			} 
	}
	
	
	private void populateException(String exceptionCode,  MessageContext messageContext) throws TransformerException {
		
		ExceptionContext exception = new ExceptionContext();
		exception.setException(true);
		exception.setCode(exceptionCode);
		messageContext.setExceptionContext(exception);
		
	}

}
