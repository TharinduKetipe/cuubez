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
package com.cuubez.example;


import com.cuubez.core.Interceptor.RequestInterceptor;
import com.cuubez.core.annotation.security.DenyAll;
import com.cuubez.core.annotation.security.PermitAll;
import com.cuubez.core.annotation.security.RolesAllowed;
import com.cuubez.core.context.InterceptorRequestContext;
import com.cuubez.core.context.InterceptorResponseContext;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFilter implements RequestInterceptor {

    private final InterceptorResponseContext responseContext = new InterceptorResponseContext("no access", HttpServletResponse.SC_FORBIDDEN);

    public InterceptorResponseContext process(InterceptorRequestContext interceptorRequestContext) {


        if (interceptorRequestContext.isAnnotationContain(DenyAll.class)) {

            String userName = new String(Base64.decodeBase64(interceptorRequestContext.getHeader("userName").getBytes()));
            String password = new String(Base64.decodeBase64(interceptorRequestContext.getHeader("password").getBytes()));


            if (userName.equals("Jhone") && password.equals("password")) {

                return null;
            }

            return responseContext;

        } else if (interceptorRequestContext.isAnnotationContain(PermitAll.class)) {

            return null;

        } else if (interceptorRequestContext.isAnnotationContain(RolesAllowed.class)) {

            String userName = new String(Base64.decodeBase64(interceptorRequestContext.getHeader("userName").getBytes()));

            String[] allocatedRoles = ((RolesAllowed) interceptorRequestContext.getAnnotation(RolesAllowed.class)).value();
            UserAccessor userAccessor = new UserAccessor();
            String role = userAccessor.getUserRole(userName);

            for (String allRole : allocatedRoles) {

                if (allRole.equals(role)) {
                    return null;
                }
            }

            return responseContext;

        }

        return null;

    }
}
