package com.cuubez.core.handler;


import com.cuubez.core.context.ApplicationConfigurationContext;
import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;

public class URIValidateHandler implements RequestHandler {


    private static String PARAMETER_SEPARATOR = "&";
    private static String PATH_SEPARATOR = "/";


    @Override
    public void handle(MessageContext messageContext) throws CuubezException {

        String applicationName = ApplicationConfigurationContext.getInstance().getApplicationName();
        HttpServletRequest request = messageContext.getRequestConfigurationContext().getRequest();
        String url = request.getRequestURL().toString();

        if (!url.contains(applicationName)) {
            throw new CuubezException("Invalid URL", CuubezException.INVALIDE_URL);
        }

        if (url.endsWith(PATH_SEPARATOR)) {
            url = url.substring(0, url.length() - 1);
        }

        String[] urlContents = url.split(applicationName);

        if (urlContents.length != 2) {
            throw new CuubezException("Invalid URL", CuubezException.INVALIDE_URL);
        }

        String serviceInfoUrl = urlContents[1];

        if (serviceInfoUrl.startsWith(PARAMETER_SEPARATOR)) {
            throw new CuubezException("No service name found", CuubezException.INVALIDE_URL);
        }
    }

}
