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

/**
 * @author ruwan
 */
public class AuthenticationToken {

    private String authToken;

    private long createdTimeInMilliseconds;

    public AuthenticationToken(String authToken) {
        super();
        this.authToken = authToken;
        this.createdTimeInMilliseconds = System.currentTimeMillis();
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the createdTimeInMilliseconds
     */
    public long getCreatedTimeInMilliseconds() {
        return createdTimeInMilliseconds;
    }

    /**
     * @param createdTimeInMilliseconds
     *            the createdTimeInMilliseconds to set
     */
    public void setCreatedTimeInMilliseconds(long createdTimeInMilliseconds) {
        this.createdTimeInMilliseconds = createdTimeInMilliseconds;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authToken == null) ? 0 : authToken.hashCode());
        result = prime * result + (int) (createdTimeInMilliseconds ^ (createdTimeInMilliseconds >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
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
        AuthenticationToken other = (AuthenticationToken) obj;
        if (authToken == null) {
            if (other.authToken != null)
                return false;
        } else if (!authToken.equals(other.authToken))
            return false;
        if (createdTimeInMilliseconds != other.createdTimeInMilliseconds)
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AuthenticationToken [authToken=" + authToken + ", createdTimeInMilliseconds="
                + createdTimeInMilliseconds + "]";
    }

}
