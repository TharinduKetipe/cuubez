package com.cuubez.core.engine.param;

import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;


public class QueryParameterHandler implements RequestHandler {

    private static String PARAMETER_SEPARATOR = "&";
    private static String NAME_VALUE_SEPARATOR = "=";

    @Override
    public void handle(RequestContext requestContext, RequestConfigurationContext requestConfigurationContext) throws CuubezException {

        HttpServletRequest httpServletRequest = requestConfigurationContext.getRequest();
        String queryString = httpServletRequest.getQueryString();

        if (queryString != null) {

            if (queryString.contains(PARAMETER_SEPARATOR)) {

                String[] queryParameters = queryString.split(PARAMETER_SEPARATOR);

                for (String queryParameter : queryParameters) {

                    String[] queryParam = queryParameter.split(NAME_VALUE_SEPARATOR);
                    requestContext.getUrlContext().addQueryVariableMetaData(queryParam[0], queryParam[1]);


                }


            } else {

                String[] queryParam = queryString.split(NAME_VALUE_SEPARATOR);
                requestContext.getUrlContext().addQueryVariableMetaData(queryParam[0], queryParam[1]);

            }


        }
    }
}
