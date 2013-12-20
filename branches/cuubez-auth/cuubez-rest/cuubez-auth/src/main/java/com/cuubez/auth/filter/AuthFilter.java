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

package com.cuubez.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.auth.AuthController;
import com.cuubez.auth.exception.UnauthorizedException;
import com.cuubez.core.context.ServiceContext;
import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.exception.CuubezException;

/**
 * 
 *
 */
public class AuthFilter implements Filter {

    private static Log log = LogFactory.getLog(AuthFilter.class);

    private AuthController authController = AuthController.getInstance();

    private static String PATH_SEPARATOR = "/";

    /**
	 * 
	 */
    public AuthFilter() {

    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        String httpMethod = ((HttpServletRequest) request).getMethod();
        String[] paths = ((HttpServletRequest) request).getPathInfo().split(PATH_SEPARATOR);

        String serviceLocation = PATH_SEPARATOR + paths[1];
        String serviceName = paths[2];

        ServiceContext serviceContext = null;

        try {
            serviceContext = ServiceRepository.getInstance().findService(httpMethod, serviceLocation, serviceName);
        } catch (CuubezException e) {
            String errMsg = "Service  [" + serviceName + "] not found. - " + e.getMessage();
            log.error(errMsg, e);
        }

        try {
            if (serviceContext != null && serviceContext.isSecure()) {
                authController.service(request, response, serviceContext);
            }
        } catch (UnauthorizedException e) {
            String errMsg = "User unauthorized. - " + e.getMessage();
            log.error(errMsg, e);
            throw new ServletException(e.getMessage());
        }

        chain.doFilter(request, response);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {

    }

}
