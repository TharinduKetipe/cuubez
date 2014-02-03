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

package com.cuubez.auth.model;

import com.cuubez.auth.model.enums.AccountAction;
import com.cuubez.auth.model.enums.AccountOutcome;

/**
 * @author ruwan
 */
public class AccountEntry {

    private String userId;
    private String roleId;
    private AccountAction accountAction;
    private String serviceName;
    private AccountOutcome accountOutcome;

    public AccountEntry(String userId, String roleId, AccountAction accountAction, String serviceName,
            AccountOutcome accountOutcome) {
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
     * @param userId
     *            the userId to set
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
     * @param roleId
     *            the roleId to set
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
     * @param accountAction
     *            the accountAction to set
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
     * @param serviceName
     *            the serviceName to set
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
     * @param accountOutcome
     *            the accountOutcome to set
     */
    public void setAccountOutcome(AccountOutcome accountOutcome) {
        this.accountOutcome = accountOutcome;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AccountEntry [userId=" + userId + ", roleId=" + roleId + ", accountAction=" + accountAction
                + ", serviceName=" + serviceName + ", accountOutcome=" + accountOutcome + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountAction == null) ? 0 : accountAction.hashCode());
        result = prime * result + ((accountOutcome == null) ? 0 : accountOutcome.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AccountEntry other = (AccountEntry) obj;
        if (accountAction != other.accountAction)
            return false;
        if (accountOutcome != other.accountOutcome)
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (serviceName == null) {
            if (other.serviceName != null)
                return false;
        } else if (!serviceName.equals(other.serviceName))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
