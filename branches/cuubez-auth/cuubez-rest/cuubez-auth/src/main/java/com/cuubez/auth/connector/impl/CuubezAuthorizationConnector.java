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

package com.cuubez.auth.connector.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.auth.connector.AuthorizationConnector;
import com.cuubez.auth.exception.AuthorizationConnectorException;
import com.cuubez.auth.model.UserProfile;
import com.cuubez.core.context.ServiceContext;

/**
 * @author ruwan
 */
public class CuubezAuthorizationConnector implements AuthorizationConnector {

    private static Log log = LogFactory.getLog(CuubezAuthorizationConnector.class);

    private static CuubezAuthorizationConnector cuubezAuthorizationConnector;

    /**
	 * 
	 */
    private CuubezAuthorizationConnector() {

    }

    public void authorize(UserProfile userProfile, ServiceContext serviceContext)
            throws AuthorizationConnectorException {

        if (serviceContext != null) {
            List<String> userIds = Arrays.asList(serviceContext.getUserIds());
            List<String> roleIds = Arrays.asList(serviceContext.getRoleIds());

            String serviceName = serviceContext.getServiceAnnotationMetaData().getServiceLocation() + "/"
                    + serviceContext.getServiceName();

            if (userIds != null && userIds.size() > 0) {
                if (userIds.contains(userProfile.getUserId())) {
                    if (log.isDebugEnabled()) {
                        log.debug("(" + userProfile.getUserId() + ") Authorized for service : " + serviceName);
                    }
                    return;
                }
            }
            if (roleIds != null && roleIds.size() > 0) {
                if (roleIds.contains(userProfile.getRoleId())) {
                    if (log.isDebugEnabled()) {
                        log.debug("(" + userProfile.getUserId() + ") Authorized for service : " + serviceName);
                    }
                    return;
                }
            }

            String errMsg = "Cuubez authorization failed. - user : " + userProfile.getUserId() + "in role : "
                    + userProfile.getRoleId() + " not authorized for service : " + serviceName;
            log.error(errMsg);
            throw new AuthorizationConnectorException(errMsg);

        }

    }

    /**
     * @return
     */
    public static CuubezAuthorizationConnector getInstance() {
        if (cuubezAuthorizationConnector == null) {
            cuubezAuthorizationConnector = new CuubezAuthorizationConnector();
        }
        return cuubezAuthorizationConnector;
    }

}
