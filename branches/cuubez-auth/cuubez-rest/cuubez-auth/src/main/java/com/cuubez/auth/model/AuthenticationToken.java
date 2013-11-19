/**
 * 
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
