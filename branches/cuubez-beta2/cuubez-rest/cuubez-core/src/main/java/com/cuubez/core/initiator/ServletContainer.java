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
package com.cuubez.core.initiator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cuubez.core.context.MessageContext;
import com.cuubez.core.engine.io.Connection;
import com.cuubez.core.engine.io.HttpConnection;
import com.cuubez.core.engine.processor.ServiceProcessor;
import com.cuubez.core.initiator.param.HttpMethods;


public class ServletContainer extends HttpServlet {

    private static final long serialVersionUID = 5466841407373731970L;

    protected void process(HttpServletRequest request, HttpServletResponse response, HttpMethods httpMethod) {
        
    	MessageContext messageContext = new ServiceProcessor().process(request, httpMethod);

        if (messageContext != null && messageContext.getContent() != null) {
            writeResponse(request, response, messageContext);
        }
    }


    public void writeResponse(HttpServletRequest request, HttpServletResponse response, MessageContext messageContext) {

        Connection connection = new HttpConnection();
        connection.write(request, response, messageContext);
    }


}
