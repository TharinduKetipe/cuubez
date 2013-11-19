/**
 * 
 */
package com.cuubez.auth.connector;

import com.cuubez.auth.exception.AccountingConnectorException;
import com.cuubez.auth.model.AccountEntry;

/**
 * @author ruwan
 *
 */
public interface AccountingConnector {
	
	public void account(AccountEntry accountEntry) throws AccountingConnectorException;
}
