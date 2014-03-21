package com.cuubez.core.handler;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.exception.CuubezException;

public class RequestHandlerChain {

    public void doChain(MessageContext messageContext)throws CuubezException{

        RequestHandler requestHandlerContextInitiate = new RequestContextInitiateHandler();
        requestHandlerContextInitiate.handle(messageContext);

        RequestHandler uriValidateHandler = new URIValidateHandler();
        uriValidateHandler.handle(messageContext);

        RequestHandler uriNormalizeHandler = new URINormalizeHandler();
        uriNormalizeHandler.handle(messageContext);

        RequestContextInitiateHandler requestContextInitiateHandler = new RequestContextInitiateHandler();
        requestContextInitiateHandler.handle(messageContext);

        HeaderParameterPopulateHandler headerParameterHandler = new HeaderParameterPopulateHandler();
        headerParameterHandler.handle(messageContext);

        QueryParameterPopulateHandler queryParameterHandler = new QueryParameterPopulateHandler();
        queryParameterHandler.handle(messageContext);

        ResourceSearchHandler resourceSearchHandler = new ResourceSearchHandler();
        resourceSearchHandler.handle(messageContext);

        ResourceInvokeHandler resourceInvokeHandler = new ResourceInvokeHandler();
        resourceInvokeHandler.handle(messageContext);

    }
}
