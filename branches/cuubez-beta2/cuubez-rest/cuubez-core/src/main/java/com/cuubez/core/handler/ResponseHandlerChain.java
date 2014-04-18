package com.cuubez.core.handler;


import com.cuubez.core.context.MessageContext;

public class ResponseHandlerChain {

    public void doChain(MessageContext messageContext) {

        ResponseTransformHandler resourceTransformHandler = new ResponseTransformHandler();
        resourceTransformHandler.handleResponse(messageContext);


    }

}
