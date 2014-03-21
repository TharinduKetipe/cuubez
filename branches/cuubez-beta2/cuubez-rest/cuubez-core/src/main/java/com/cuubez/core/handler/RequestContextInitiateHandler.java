package com.cuubez.core.handler;


import com.cuubez.core.context.*;
import com.cuubez.core.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;

public class RequestContextInitiateHandler implements RequestHandler {

    @Override
    public void handle(MessageContext messageContext) {
        URLContext urlContext = new URLContext();
        urlContext.setMediaType(messageContext.getRequestConfigurationContext().getRequest().getContentType());
        messageContext.getRequestContext().setUrlContext(urlContext);
        messageContext.getRequestContext().setHttpMethod(messageContext.getRequestConfigurationContext().getHttpMethod());
        messageContext.getRequestContext().getUrlContext().setServiceLocation(messageContext.getRequestConfigurationContext().getRequest().getPathInfo());

    }


}
