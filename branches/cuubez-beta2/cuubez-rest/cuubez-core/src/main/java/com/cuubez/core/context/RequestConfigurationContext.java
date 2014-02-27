package com.cuubez.core.context;


import javax.servlet.http.HttpServletRequest;
public class RequestConfigurationContext {

    private HttpServletRequest request;
    private String httpMethod;

    public RequestConfigurationContext(String httpMethod, HttpServletRequest request) {
        this.httpMethod = httpMethod;
        this.request = request;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
