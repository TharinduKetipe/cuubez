/**
 * 
 */
package com.cuubez.auth.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.auth.connector.AccountingConnector;
import com.cuubez.auth.connector.AuthenticationConnector;
import com.cuubez.auth.connector.AuthorizationConnector;
import com.cuubez.auth.connector.impl.CuubezAuthorizationConnector;
import com.cuubez.auth.connector.impl.FileAccountingConnector;
import com.cuubez.auth.connector.impl.LDAPAuthenticationConnector;
import com.cuubez.auth.exception.AccountingConnectorException;

/**
 * @author ruwan
 * 
 */
public class ConnectorUtil {

    private static Log log = LogFactory.getLog(ConnectorUtil.class);

    private static final boolean failOnAccountError = true;
    private static final boolean guaranteedAccount = true;

    public static AuthenticationConnector getAuthenticationConnector() {
        return (AuthenticationConnector) LDAPAuthenticationConnector.getInstance();
    }

    public static AuthorizationConnector getAuthorizationConnector() {
        return (AuthorizationConnector) CuubezAuthorizationConnector.getInstance();
    }

    public static AccountingConnector getAccountingConnector() {
        try {
            return (AccountingConnector) FileAccountingConnector.getInstance(failOnAccountError, guaranteedAccount);
        } catch (AccountingConnectorException e) {
            String errMsg = "FileAccountingConnector instantiation failed. Accounting is disabled :" + e.getMessage();
            log.fatal(errMsg, e);
            return null;
        }
    }
}
