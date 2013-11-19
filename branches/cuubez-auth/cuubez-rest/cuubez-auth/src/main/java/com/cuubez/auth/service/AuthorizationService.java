/**
 * 
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
