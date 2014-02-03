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
package com.cuubez.client.engine.executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.client.connection.Connection;
import com.cuubez.client.connection.HttpConnection;
import com.cuubez.client.context.MessageContext;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.param.HttpMethod;

public class ServicExecutor {
	
	private static Log log = LogFactory.getLog(ServicExecutor.class);	
	
	
	public void execute(MessageContext messageContext) throws CuubezException {
		
		Connection connection = new HttpConnection();
		
		connection.connect(messageContext);
			
		if(messageContext.getRequestContext().getHttpMethod().equals(HttpMethod.GET) && messageContext.getRequestContext().getParameters() != null && messageContext.getRequestContext().getParameters().length > 0) {
			log.error("Cannot parse parameters via GET HTTP Method");
			throw new CuubezException();
		}
			
		if(!messageContext.getRequestContext().getHttpMethod().equals(HttpMethod.GET)) {
			connection.writeContent(messageContext);
		} 
		
	}

}
