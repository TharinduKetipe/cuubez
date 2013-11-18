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
package com.cuubez.core.engine.parser.url;

import javax.servlet.http.HttpServletRequest;

import com.cuubez.core.context.ApplicationConfigurationContext;
import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.engine.parser.util.URLValidatorUtil;
import com.cuubez.core.exception.CuubezException;

public class URLParser {

    private static String PARAMETER_SEPARATOR = "&";
    private static String PATH_SEPARATOR = "/";
    private static String QUERY_PARAMETER_SEPARATOR = "/?";


    public void parse(ConfigurationContext configurationContext) throws CuubezException {
        URLValidatorUtil.validate(configurationContext);
        populateServiceDetails(configurationContext);

    }


    private void populateServiceDetails(ConfigurationContext configurationContext) {

        HttpServletRequest request = configurationContext.getRequest();
        String url = request.getRequestURL().toString();
        String applicationName = ApplicationConfigurationContext.getInstance().getApplicationName();

        String serviceName;
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

            int lastIndex = serviceInfoUrl.lastIndexOf(PATH_SEPARATOR);
            serviceLocation = PATH_SEPARATOR.concat(serviceInfoUrl.substring(0, lastIndex));
            serviceName = serviceInfoUrl.substring(lastIndex + 1, serviceInfoUrl.length());

        } else {

            serviceName = serviceInfoUrl;
            serviceLocation = PATH_SEPARATOR;

        }


        configurationContext.getUrlContext().setServiceName(serviceName);
        configurationContext.getUrlContext().setServiceLocation(serviceLocation);
        configurationContext.getUrlContext().setServiceUrl(url);
    }


}
