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

import com.cuubez.auth.connector.AccountingConnector;
import com.cuubez.auth.exception.AccountingConnectorException;
import com.cuubez.auth.exception.AccountingException;
import com.cuubez.auth.model.AccountEntry;
import com.cuubez.auth.model.enums.AccountAction;
import com.cuubez.auth.model.enums.AccountOutcome;
import com.cuubez.auth.util.ConnectorUtil;

/**
 * @author ruwan
 */
public class AccountingService {

    private static Log log = LogFactory.getLog(AccountingService.class);

    private AccountingConnector accountingConnector = ConnectorUtil.getAccountingConnector();

    public void account(String userId, String roleId, AccountAction accountAction, String serviceName,
            AccountOutcome accountOutcome) throws AccountingException {

        AccountEntry accountEntry = new AccountEntry(userId, roleId, accountAction, serviceName, accountOutcome);
        try {
            accountingConnector.account(accountEntry);// TODO check accountingConnector for null
                                                      // when failing to create connector
        } catch (AccountingConnectorException e) {
            String errMsg = "Accounting failed. - " + e.getMessage();
            log.error(errMsg, e);
            throw new AccountingException(errMsg, e);
        }

        return;
    }

}
