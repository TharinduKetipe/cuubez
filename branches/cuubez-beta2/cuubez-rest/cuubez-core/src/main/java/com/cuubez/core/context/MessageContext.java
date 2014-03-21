package com.cuubez.core.context;


public class MessageContext {

    private RequestContext requestContext;
    private ResponseContext responseContext;
    private RequestConfigurationContext requestConfigurationContext;


    public RequestContext getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public ResponseContext getResponseContext() {
        return responseContext;
    }

    public void setResponseContext(ResponseContext responseContext) {
        this.responseContext = responseContext;
    }

    public RequestConfigurationContext getRequestConfigurationContext() {
        return requestConfigurationContext;
    }

    public void setRequestConfigurationContext(RequestConfigurationContext requestConfigurationContext) {
        this.requestConfigurationContext = requestConfigurationContext;
    }
}
