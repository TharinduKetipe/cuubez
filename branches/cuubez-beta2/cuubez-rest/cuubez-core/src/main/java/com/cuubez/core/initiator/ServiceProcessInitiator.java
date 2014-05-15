package com.cuubez.core.initiator;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.context.ResponseContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.handler.RequestHandlerChain;
import com.cuubez.core.handler.ResponseHandlerChain;

import javax.servlet.http.HttpServletRequest;

public class ServiceProcessInitiator {

    public ResponseContext process(HttpServletRequest request, String httpMethod) {

        RequestConfigurationContext requestConfigurationContext = new RequestConfigurationContext(httpMethod, request);
        MessageContext messageContext = new MessageContext();
        messageContext.setRequestContext(new RequestContext());
        messageContext.setResponseContext(new ResponseContext());
        messageContext.setRequestConfigurationContext(requestConfigurationContext);

        RequestHandlerChain requestHandlerChain = new RequestHandlerChain();
        ResponseHandlerChain responseHandlerChain = new ResponseHandlerChain();


        try {

            requestHandlerChain.doChain(messageContext);
            responseHandlerChain.doChain(messageContext);


        } catch (CuubezException e) {

        }


        return messageContext.getResponseContext();
    }

}
