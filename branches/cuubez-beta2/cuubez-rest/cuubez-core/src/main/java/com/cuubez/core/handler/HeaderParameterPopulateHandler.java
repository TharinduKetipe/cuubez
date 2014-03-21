package com.cuubez.core.handler;

import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


public class HeaderParameterPopulateHandler implements RequestHandler {

    @Override
    public void handle(MessageContext messageContext) throws CuubezException {

        HttpServletRequest request = messageContext.getRequestConfigurationContext().getRequest();
        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {

            String paramName = (String) headerNames.nextElement();
            String paramValue = request.getHeader(paramName);

            messageContext.getRequestContext().getUrlContext().addHeaderVariableMetaData(paramName, paramValue);
        }
    }
}
