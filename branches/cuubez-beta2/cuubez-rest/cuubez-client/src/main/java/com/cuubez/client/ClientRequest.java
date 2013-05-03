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
package com.cuubez.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.client.context.MessageContext;
import com.cuubez.client.context.RequestContext;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.param.HttpMethod;
import com.cuubez.client.param.MediaType;
import com.cuubez.client.engine.processor.Processor;
import com.cuubez.client.engine.processor.RequestProcessor;

public class ClientRequest {
	private static Log log = LogFactory.getLog(ClientRequest.class);	
	
	private String serviceUrl;
	private MediaType mediaType;
	private Object[] parameters;
	
	public ClientRequest() {
	}
	
	public ClientRequest(String serviceUrl, MediaType mediaType) {
		this.serviceUrl = serviceUrl;
		this.mediaType = mediaType;
	}
	
	public void addParameters(Object... parameters) {
		this.parameters = parameters;
	}
	
	public void get() throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.GET);
		Processor processor = new RequestProcessor();
		processor.process(msgContext);
	}
	
	public <T> T get(Class<T> returnType) throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.GET);
		Processor processor = new RequestProcessor();
		return processor.process(msgContext,returnType);
	}
	
	public void post() throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.POST);
		Processor processor = new RequestProcessor();
		processor.process(msgContext);
	}
	
	public <T> T post(Class<T> returnType) throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.POST);
		Processor processor = new RequestProcessor();
		processor.process(msgContext);
		return processor.process(msgContext, returnType);
	}

	public void delete() throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.DELETE);
		Processor processor = new RequestProcessor();
		processor.process(msgContext);
		processor.process(msgContext);
	}
	
	public <T> T delete(Class<T> returnType) throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.DELETE);
		Processor processor = new RequestProcessor();
		processor.process(msgContext);
		return processor.process(msgContext, returnType);
	}
	
	public void put() throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.PUT);
		Processor processor = new RequestProcessor();
		processor.process(msgContext);
	}
	
	public <T> T put(Class<T> returnType) throws CuubezException {
		validateContent();
		MessageContext msgContext = getMessageContext(HttpMethod.PUT);
		Processor processor = new RequestProcessor();
		return processor.process(msgContext, returnType);
	}
	
	
	private MessageContext getMessageContext(HttpMethod httpMethod) {
		MessageContext msgContext = new MessageContext();
		RequestContext requestContext = new RequestContext(serviceUrl, mediaType, httpMethod);
		requestContext.setParameters(parameters);
		msgContext.setRequestContext(requestContext);
		return msgContext;
	}
	
	private void validateContent() throws CuubezException {
		
		if(serviceUrl == null || mediaType == null) {
			log.error("ServiceURL and MediaType cant be a null");
			throw new CuubezException();
		}
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public Object[] getParameters() {
		return parameters;
	}

}
