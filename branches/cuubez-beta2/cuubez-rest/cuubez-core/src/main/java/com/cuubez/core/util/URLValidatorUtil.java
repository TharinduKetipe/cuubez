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
package com.cuubez.core.util;

import javax.servlet.http.HttpServletRequest;

import com.cuubez.core.context.ApplicationConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.exception.CuubezException;

public class URLValidatorUtil {


    private static String PARAMETER_SEPARATOR = "&";
    private static String PATH_SEPARATOR = "/";

    public static void validate(RequestContext requestContext) throws CuubezException {

        String applicationName = ApplicationConfigurationContext.getInstance().getApplicationName();
        HttpServletRequest request = requestContext.getRequest();
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
