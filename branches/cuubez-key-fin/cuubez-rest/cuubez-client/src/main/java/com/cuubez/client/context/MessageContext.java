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

public class MessageContext {
	
	private RequestContext requestContext;
	private Object returnObject;
	private Class<?> returnType;
	private ExceptionContext exceptionContext;
	private KeyContext keyContext; 

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	
	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	public ExceptionContext getExceptionContext() {
		return exceptionContext;
	}

	public void setExceptionContext(ExceptionContext exceptionContext) {
		this.exceptionContext = exceptionContext;
	}

	/**
	 * @return the keyContext
	 */
	public KeyContext getKeyContext() {
		return keyContext;
	}

	/**
	 * @param keyContext the keyContext to set
	 */
	public void setKeyContext(KeyContext keyContext) {
		this.keyContext = keyContext;
	}
	

}
