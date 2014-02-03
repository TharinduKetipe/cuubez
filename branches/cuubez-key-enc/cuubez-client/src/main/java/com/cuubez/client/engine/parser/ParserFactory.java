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
package com.cuubez.client.engine.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.client.context.MessageContext;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.param.MediaType;
import com.cuubez.client.engine.parser.content.ContentParser;
import com.cuubez.client.engine.parser.content.xml.XMLContentParser;
import com.cuubez.client.engine.parser.content.xml.XMLExceptionParser;
import com.cuubez.client.engine.parser.url.URLParser;
import com.cuubez.client.engine.parser.url.xml.XMLURLParser;

public class ParserFactory {
	
	private static Log log = LogFactory.getLog(ParserFactory.class);	
	
	public void parse(MessageContext messageContext, int parserType) throws CuubezException {

		try {
			
		
        if(MediaType.XML.equals(messageContext.getRequestContext().getMediaType())) {
			
			if(parserType == Parser.CONTENT) {
				
				ContentParser parser = new XMLContentParser();
				parser.parse(messageContext);
				
			} else if(parserType == Parser.PARAMETER){
				
				URLParser parser = new XMLURLParser();
				parser.parse(messageContext);
				
			} else if(parserType == Parser.EXCEPTION){
				
				ContentParser parser = new XMLExceptionParser();
				parser.parse(messageContext);
			}
			
		}
        
		} catch(CuubezException e) {
			log.error("Exception occure while parsing contents.",e);
			throw new CuubezException(e);
		}

       
	}


}
