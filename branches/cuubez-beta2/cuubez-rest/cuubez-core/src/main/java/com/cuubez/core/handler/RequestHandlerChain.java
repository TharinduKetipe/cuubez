package com.cuubez.core.handler;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.exception.CuubezException;

public class RequestHandlerChain {

    public void doChain(MessageContext messageContext)throws CuubezException{

        RequestHandler requestHandlerContextInitiate = new RequestContextInitiateHandler();
        requestHandlerContextInitiate.handleRequest(messageContext);

        RequestHandler uriValidateHandler = new URIValidateHandler();
        uriValidateHandler.handleRequest(messageContext);

        RequestHandler uriNormalizeHandler = new URINormalizeHandler();
        uriNormalizeHandler.handleRequest(messageContext);

        RequestContextInitiateHandler requestContextInitiateHandler = new RequestContextInitiateHandler();
        requestContextInitiateHandler.handleRequest(messageContext);

        HeaderParameterPopulateHandler headerParameterHandler = new HeaderParameterPopulateHandler();
        headerParameterHandler.handleRequest(messageContext);

        QueryParameterPopulateHandler queryParameterHandler = new QueryParameterPopulateHandler();
        queryParameterHandler.handleRequest(messageContext);

        ResourceSearchHandler resourceSearchHandler = new ResourceSearchHandler();
        resourceSearchHandler.handleRequest(messageContext);

        InvocationParametersHandler invocationParametersHandler = new InvocationParametersHandler();
        invocationParametersHandler.handleRequest(messageContext);

        RequestTransformHandler requestTransformHandler = new RequestTransformHandler();
        requestTransformHandler.handleRequest(messageContext);

        ResourceInvokeHandler resourceInvokeHandler = new ResourceInvokeHandler();
        resourceInvokeHandler.handleRequest(messageContext);

    }
}
