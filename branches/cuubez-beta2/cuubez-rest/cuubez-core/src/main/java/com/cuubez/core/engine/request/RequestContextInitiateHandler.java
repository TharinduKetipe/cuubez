package com.cuubez.core.engine.request;


import com.cuubez.core.context.ApplicationConfigurationContext;
import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.context.URLContext;
import com.cuubez.core.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;

public class RequestContextInitiateHandler implements RequestHandler {

    private static String PARAMETER_SEPARATOR = "&";
        private static String PATH_SEPARATOR = "/";
        private static String QUERY_PARAMETER_SEPARATOR = "/?";



    @Override
    public void handle(RequestContext requestContext, RequestConfigurationContext requestConfigurationContext) {
        URLContext urlContext = new URLContext();
        urlContext.setMediaType(requestConfigurationContext.getRequest().getContentType());
        requestContext.setUrlContext(urlContext);
        requestContext.setHttpMethod(requestConfigurationContext.getHttpMethod());
        requestContext.getUrlContext().setServiceLocation(requestConfigurationContext.getRequest().getPathInfo());

    }


}
