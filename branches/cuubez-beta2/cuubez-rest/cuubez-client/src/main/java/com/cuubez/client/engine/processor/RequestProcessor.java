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
package com.cuubez.client.engine.processor;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.cuubez.client.context.MessageContext;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.exception.ExceptionTransformer;
import com.cuubez.client.engine.executor.ServicExecutor;
import com.cuubez.client.engine.parser.Parser;
import com.cuubez.client.engine.parser.ParserFactory;
import com.cuubez.client.engine.parser.util.XMLParserUtil;
import com.cuubez.client.engine.processor.util.ParameterProcessorUtil;
																												
public class RequestProcessor implements Processor{

	private static Log log = LogFactory.getLog(RequestProcessor.class);	
	
	@Override
	public <T> T process(MessageContext messageContext, Class<T> returnType) throws CuubezException {
		

		try {
		
			ParameterProcessorUtil.prepareParameter(messageContext);
			ServicExecutor executor = new ServicExecutor();
			executor.execute(messageContext);
			
			populateContent(messageContext);
			
			ParserFactory parserFactory = new ParserFactory();
			parserFactory.parse(messageContext,Parser.EXCEPTION);
			
			if(returnType != null && messageContext.getExceptionContext() == null) {
				messageContext.setReturnType(returnType);
				parserFactory.parse(messageContext,Parser.CONTENT);
			}
			
			if(messageContext.getExceptionContext() != null) {
				ExceptionTransformer.transform(messageContext);
				log.error(messageContext.getExceptionContext().getMessage());
				throw new CuubezException();
			}
		
		} catch (CuubezException e) {
			log.error(e);
			throw e;
		}
		
		Object returnObject = messageContext.getReturnObject();
		
		if(returnObject == null) {
			return null;
		}
		
		return returnType.cast(returnObject);
		
	}

	@Override
	public void process(MessageContext messageContext) throws CuubezException {
		
		try {
			
			ParameterProcessorUtil.prepareParameter(messageContext);
			ServicExecutor executor = new ServicExecutor();
			executor.execute(messageContext);
			
			populateContent(messageContext);
			
			ParserFactory parserFactory = new ParserFactory();
			parserFactory.parse(messageContext,Parser.EXCEPTION);
			
			
			if(messageContext.getExceptionContext() != null) {
				ExceptionTransformer.transform(messageContext);
				log.error(messageContext.getExceptionContext().getMessage());
				throw new CuubezException();
			}
		
		} catch (CuubezException e) {
			log.error(e);
			throw e;
		}
		
	}
	
	private void populateContent(MessageContext messageContext) {
		
		HttpURLConnection urlConnection = messageContext.getRequestContext().getHttpURLConnection();
		
		if(urlConnection != null) {
			
			InputStream inputStream = null;
			
			try {
					
				inputStream = urlConnection.getInputStream();
				Document doc = XMLParserUtil.createDocument(inputStream);
				messageContext.getRequestContext().setDocument(doc);
			
			} catch (IOException e) {
				log.error("Exception occure while parsing response details", e);
			} catch (ParserConfigurationException e) {
				log.error("Exception occure while parsing response details", e);
			} catch (SAXException e) {
				log.error("Exception occure while parsing response details", e);
			} finally {
				
				if(inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						log.error(e);
					}
				}
				urlConnection.disconnect();
			}
			
		}
		
		
	}
}
