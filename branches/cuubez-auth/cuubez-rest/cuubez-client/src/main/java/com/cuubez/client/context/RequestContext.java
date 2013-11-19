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
package com.cuubez.client.context;

import java.net.HttpURLConnection;

import org.w3c.dom.Document;

import com.cuubez.client.param.HttpMethod;
import com.cuubez.client.param.MediaType;

public class RequestContext {

	private String serviceUrl;
	private MediaType mediaType;
	private HttpMethod httpMethod;
	private HttpURLConnection httpURLConnection;
	private Object[] parameters;
	private String principal;
	private String credentials;
	private String transformedParameter;
	private Document document;
	

	public RequestContext(String serviceUrl, MediaType mediaType, HttpMethod httpMethod) {
		super();
		this.serviceUrl = serviceUrl;
		this.mediaType = mediaType;
		this.httpMethod = httpMethod;
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

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public HttpURLConnection getHttpURLConnection() {
		return httpURLConnection;
	}

	public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
		this.httpURLConnection = httpURLConnection;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public String getTransformedParameter() {
		return transformedParameter;
	}

	public void setTransformedParameter(String transformedParameter) {
		this.transformedParameter = transformedParameter;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

    /**
     * @return the principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * @return the credentials
     */
    public String getCredentials() {
        return credentials;
    }

    /**
     * @param credentials the credentials to set
     */
    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

	
}
