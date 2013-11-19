/**
 * 
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
