/**
 * 
 */
package com.cuubez.auth.model;

import com.cuubez.auth.model.enums.AccountAction;
import com.cuubez.auth.model.enums.AccountOutcome;

/**
 * @author ruwan
 *
 */
public class AccountEntry {

	private String userId;
	private String roleId;
	private AccountAction accountAction;
	private String serviceName;
	private AccountOutcome accountOutcome;
		
	public AccountEntry(String userId, String roleId,
			AccountAction accountAction, String serviceName, AccountOutcome accountOutcome) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.accountAction = accountAction;
		this.serviceName = serviceName;
		this.accountOutcome = accountOutcome;
	}

	/**
	 * 
	 */
	public AccountEntry() {
	    
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the accountAction
	 */
	public AccountAction getAccountAction() {
		return accountAction;
	}

	/**
	 * @param accountAction the accountAction to set
	 */
	public void setAccountAction(AccountAction accountAction) {
		this.accountAction = accountAction;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
     * @return the accountOutcome
     */
    public AccountOutcome getAccountOutcome() {
        return accountOutcome;
    }

    /**
     * @param accountOutcome the accountOutcome to set
     */
    public void setAccountOutcome(AccountOutcome accountOutcome) {
        this.accountOutcome = accountOutcome;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountEntry [userId=" + userId + ", roleId=" + roleId
				+ ", accountAction=" + accountAction + ", serviceName="
				+ serviceName + ", accountOutcome=" + accountOutcome + "]";
	}
	
}
