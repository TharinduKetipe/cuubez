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

import com.cuubez.client.security.DocumentDecrypter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cuubez.client.context.MessageContext;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.engine.parser.content.ContentParser;
import com.cuubez.client.engine.parser.util.XMLParserUtil;

public class XMLContentParser implements ContentParser{

	private static final String BODY_ELEMENT = "Body";

	@Override
	public void parse(MessageContext messageContext) throws CuubezException {

		try {

			Document doc = messageContext.getRequestContext().getDocument();
			
		    if(doc != null) {

                try {
                   doc = DocumentDecrypter.decrypt(doc);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                List<Element> returnElement = XMLParserUtil.getElements(BODY_ELEMENT, doc);
				populateReturnObjects(returnElement, messageContext);
                    
            }
				
			} catch (TransformerException e) {
				throw new CuubezException(e);
			} 

    }
	
	
	private void populateReturnObjects(List<Element> elements,  MessageContext messageContext) throws TransformerException {
		
		if(elements != null && elements.size() > 0) {
			String content = XMLParserUtil.getElementContent(elements.get(0));
			Object object = UnMarshaller.unMarshal(content,messageContext.getReturnType());
			messageContext.setReturnObject(object);
			
		}
		
	}
	
}
