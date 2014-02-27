package com.cuubez.core.handler;


import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.engine.param.HeaderParameterHandler;
import com.cuubez.core.engine.param.QueryParameterHandler;
import com.cuubez.core.engine.request.RequestContextInitiateHandler;
import com.cuubez.core.engine.uri.URINormalizeHandler;
import com.cuubez.core.engine.uri.URIValidateHandler;
import com.cuubez.core.exception.CuubezException;

public class RequestHandlerChain {

    public void doChain(RequestContext requestContext, RequestConfigurationContext requestConfigurationContext)throws CuubezException{

        RequestHandler requestHandlerContextInitiate = new RequestContextInitiateHandler();
        requestHandlerContextInitiate.handle(requestContext, requestConfigurationContext);

        RequestHandler uriValidateHandler = new URIValidateHandler();
        uriValidateHandler.handle(requestContext, requestConfigurationContext);

        RequestHandler uriNormalizeHandler = new URINormalizeHandler();
        uriNormalizeHandler.handle(requestContext, requestConfigurationContext);

        RequestContextInitiateHandler requestContextInitiateHandler = new RequestContextInitiateHandler();
        requestContextInitiateHandler.handle(requestContext, requestConfigurationContext);

        HeaderParameterHandler headerParameterHandler = new HeaderParameterHandler();
        headerParameterHandler.handle(requestContext, requestConfigurationContext);

        QueryParameterHandler queryParameterHandler = new QueryParameterHandler();
        queryParameterHandler.handle(requestContext, requestConfigurationContext);

    }
}
