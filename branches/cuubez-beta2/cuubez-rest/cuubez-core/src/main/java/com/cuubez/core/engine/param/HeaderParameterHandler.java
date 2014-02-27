package com.cuubez.core.engine.param;

import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


public class HeaderParameterHandler implements RequestHandler {

    @Override
    public void handle(RequestContext requestContext, RequestConfigurationContext requestConfigurationContext) throws CuubezException {

        HttpServletRequest request = requestConfigurationContext.getRequest();
        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {

            String paramName = (String) headerNames.nextElement();
            String paramValue = request.getHeader(paramName);

            requestContext.getUrlContext().addHeaderVariableMetaData(paramName, paramValue);
        }
    }
}
