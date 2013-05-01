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
package com.cuubez.client.exception;

import com.cuubez.client.context.MessageContext;

public class ExceptionTransformer {
	
	
	public static void transform(MessageContext messageContext) {
		int exceptionCode = 0;
		
		String code = messageContext.getExceptionContext().getCode();
		
		if(code != null) {
			exceptionCode = Integer.parseInt(code);
		}
		
		if(CuubezExceptionConstance.INTERNAL_EXCEPTION == exceptionCode) {
			messageContext.getExceptionContext().setMessage("Internal Exception");
		} else if(CuubezExceptionConstance.INVALIDE_PARAMETERS == exceptionCode) {
			messageContext.getExceptionContext().setMessage("Invalide Parameters");
		} else if (CuubezExceptionConstance.INVALIDE_URL == exceptionCode) {
			messageContext.getExceptionContext().setMessage("Invalide URL");
		} else if (CuubezExceptionConstance.SERVICE_NOT_FOUND == exceptionCode) {
			messageContext.getExceptionContext().setMessage("Service not found"); 
		} 
	}

}
