/**
 * 
 */
package com.cuubez.auth.connector;

import com.cuubez.auth.exception.AuthenticationConnectorException;
import com.cuubez.auth.model.AuthenticationCode;
import com.cuubez.auth.model.AuthenticationToken;

/**
 * @author ruwan
 */
public interface AuthenticationConnector {

    public AuthenticationToken authenticate(AuthenticationCode authenticationCode)
            throws AuthenticationConnectorException;

    public String getRole(String userId) throws AuthenticationConnectorException;
}
