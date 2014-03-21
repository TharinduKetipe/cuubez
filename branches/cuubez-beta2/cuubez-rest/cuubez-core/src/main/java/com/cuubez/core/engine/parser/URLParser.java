/**
 *	Copyright [2013] [www.cuubez.com]
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package com.cuubez.core.engine.parser;

import com.cuubez.core.context.RequestContext;
import com.cuubez.core.util.URLValidatorUtil;
import com.cuubez.core.exception.CuubezException;

public class URLParser {

    private static String PARAMETER_SEPARATOR = "&";
    private static String PARAMETER_SEPARATOR_VALUE_SEPARATOR = "=";
    private static String PATH_SEPARATOR = "/";
    private static String QUERY_PARAMETER_SEPARATOR = "/?";


    public void parse(RequestContext requestContext) throws CuubezException {
        URLValidatorUtil.validate(requestContext);
      //  populateServiceDetails(requestContext);

    }

  /*
    private void populateServiceDetails(RequestContext requestContext) {

        HttpServletRequest request = requestContext.getRequest();
        String url = request.getRequestURL().toString();
        String applicationName = ApplicationConfigurationContext.getInstance().getApplicationName();

        String serviceLocation;

        if (url.contains(QUERY_PARAMETER_SEPARATOR)) {
            url = url.split(QUERY_PARAMETER_SEPARATOR)[0];
        }

        if (url.endsWith(PATH_SEPARATOR)) {
            url = url.substring(0, url.length() - 1);
        }

        String[] urlContents = url.split(applicationName);
        String serviceInfoUrl = urlContents[1];

        if (serviceInfoUrl.contains(PARAMETER_SEPARATOR)) {
            serviceInfoUrl = serviceInfoUrl.split(PARAMETER_SEPARATOR)[0];
        }

        if (serviceInfoUrl.startsWith(PATH_SEPARATOR)) {

            serviceInfoUrl = serviceInfoUrl.substring(1, serviceInfoUrl.length());
            
        }

        if (serviceInfoUrl.endsWith(PATH_SEPARATOR)) {

            serviceInfoUrl = serviceInfoUrl.substring(0, serviceInfoUrl.length() - 1);
        }

        if (serviceInfoUrl.contains(PATH_SEPARATOR)) {
            serviceLocation = PATH_SEPARATOR.concat(serviceInfoUrl);
        } else {

            serviceLocation = PATH_SEPARATOR;

        }


        requestContext.getUrlContext().setServiceLocation(serviceLocation);
        requestContext.getUrlContext().setServiceUrl(url);
        populateQueryParameters(requestContext.getUrlContext());

    }


    private void populateQueryParameters(URLContext urlContext) {

        String url = urlContext.getServiceUrl();
        List<QueryVariableMetaData> queryVariableMetaDataList = new ArrayList<QueryVariableMetaData>();

        if(url.contains(QUERY_PARAMETER_SEPARATOR)) {
            String[] queryParameters = url.split(QUERY_PARAMETER_SEPARATOR);

            if(queryParameters.length > 1 && !queryParameters[1].isEmpty()) {

                String queryVariableURI = queryParameters[1];

                String[] queryVariables = queryVariableURI.split(PARAMETER_SEPARATOR);

                for(String queryVariable : queryParameters) {

                    if(queryVariable.contains(PARAMETER_SEPARATOR_VALUE_SEPARATOR)){

                           String[] parameterNameAndValue = queryVariable.split(PARAMETER_SEPARATOR_VALUE_SEPARATOR);

                        if(parameterNameAndValue.length == 2) {

                            String name = parameterNameAndValue[0];
                            String value = parameterNameAndValue[1];

                            QueryVariableMetaData queryVariableMetaData = new QueryVariableMetaData(name, value);
                            queryVariableMetaDataList.add(queryVariableMetaData);

                        }

                    }

                }


            }
        }

        urlContext.setQueryVariableMetaDataList(queryVariableMetaDataList);

    }
              */

}
