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

import com.cuubez.auth.connector.AuthenticationConnector;
import com.cuubez.auth.exception.AuthenticationConnectorException;
import com.cuubez.auth.exception.AuthenticationException;
import com.cuubez.auth.model.AuthenticationCode;
import com.cuubez.auth.model.AuthenticationToken;
import com.cuubez.auth.model.UserProfile;
import com.cuubez.auth.util.ConnectorUtil;

/**
 * @author ruwan
 */
public class AuthenticationService {

    private static Log log = LogFactory.getLog(AuthenticationService.class);

    private AuthenticationConnector authenticationConnector = ConnectorUtil.getAuthenticationConnector();

    public UserProfile authenticate(AuthenticationCode authenticationCode) throws AuthenticationException {
        UserProfile userProfile = new UserProfile();
        AuthenticationToken authenticationToken = null;
        String roleId = null;

        try {
            authenticationToken = authenticationConnector.authenticate(authenticationCode);
        } catch (AuthenticationConnectorException e) {
            String errMsg = "Authentication failed. - " + e.getMessage();
            log.error(errMsg);
            throw new AuthenticationException(errMsg, e);
        }

        try {
            roleId = authenticationConnector.getRole(authenticationCode.getPrincipal());
        } catch (AuthenticationConnectorException e) {
            String errMsg = "Role retrieval failed. - " + e.getMessage();
            log.error(errMsg);
            throw new AuthenticationException(errMsg, e);
        }

        userProfile.setAuthenticationToken(authenticationToken);
        userProfile.setUserId(authenticationCode.getPrincipal());
        userProfile.setRoleId(roleId);

        return userProfile;
    }
}
