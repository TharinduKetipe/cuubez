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
package com.cuubez.core.engine.parser.url.xml;

import javax.servlet.http.HttpServletRequest;

import com.cuubez.core.context.ApplicationConfigurationContext;
import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.engine.parser.url.URLParser;
import com.cuubez.core.engine.parser.util.URLValidatorUtil;

public class XMLURLParser implements URLParser {

    private static String PARAMETER_SEPARATOR = "&";
    private static String PATH_SEPARATOR = "/";
    private static String PATH_PARAMETER_SEPARATOR = "/?";


    public void parse(ConfigurationContext configurationContext) throws CuubezException {
        URLValidatorUtil.validate(configurationContext);
        populateServiceDetails(configurationContext);

    }


    private void populateServiceDetails(ConfigurationContext configurationContext) {

        HttpServletRequest request = configurationContext.getRequest();
        String url = request.getRequestURL().toString();
        String applicationName = ApplicationConfigurationContext.getInstance().getApplicationName();

        String path;

        if (url.contains(PATH_PARAMETER_SEPARATOR)) {
            url = url.split(PATH_PARAMETER_SEPARATOR)[0];
        }

        if (url.endsWith(PATH_SEPARATOR)) {
            url = url.substring(0, url.length() - 1);
        }

        String[] urlContents = url.split(applicationName);


        if(urlContents.length > 1) {

            String serviceInfoUrl = urlContents[1];

            if (serviceInfoUrl.startsWith(PATH_SEPARATOR)) {

                serviceInfoUrl = serviceInfoUrl.substring(1, serviceInfoUrl.length());
            }

            if (serviceInfoUrl.endsWith(PATH_SEPARATOR)) {

                serviceInfoUrl = serviceInfoUrl.substring(0, serviceInfoUrl.length() - 1);
            }

            path = serviceInfoUrl;

        } else {
            path = PATH_SEPARATOR;
        }


        configurationContext.getUrlContext().setPath(path);
        configurationContext.getUrlContext().setServiceUrl(url);
    }


}
