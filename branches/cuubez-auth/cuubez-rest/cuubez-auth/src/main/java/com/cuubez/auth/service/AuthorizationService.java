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

package com.cuubez.auth.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.auth.connector.AuthorizationConnector;
import com.cuubez.auth.exception.AuthorizationConnectorException;
import com.cuubez.auth.exception.AuthorizationException;
import com.cuubez.auth.model.UserProfile;
import com.cuubez.auth.util.ConnectorUtil;
import com.cuubez.core.context.ServiceContext;

/**
 * @author ruwan
 */
public class AuthorizationService {

    private static Log log = LogFactory.getLog(AuthorizationService.class);

    private AuthorizationConnector authorizationConnector = ConnectorUtil.getAuthorizationConnector();

    public void authorize(UserProfile userProfile, ServiceContext serviceContext) throws AuthorizationException {

        try {
            authorizationConnector.authorize(userProfile, serviceContext);
        } catch (AuthorizationConnectorException e) {
            String errMsg = "Authorization failed. - " + e.getMessage();
            log.error(errMsg, e);
            throw new AuthorizationException(errMsg, e);
        }

        return;
    }

}
