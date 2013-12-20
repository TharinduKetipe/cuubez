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
