/**
 * 
 */
package com.cuubez.auth.connector;

import com.cuubez.auth.exception.AuthorizationConnectorException;
import com.cuubez.auth.model.UserProfile;
import com.cuubez.core.context.ServiceContext;

/**
 * @author ruwan
 */
public interface AuthorizationConnector {

    public void authorize(UserProfile userProfile, ServiceContext serviceContext)
            throws AuthorizationConnectorException;
}
