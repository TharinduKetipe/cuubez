/**
 * 
 */
package com.cuubez.auth.model;

/**
 * @author ruwan
 */
public class AuthenticationCode {

    private String principal;
    private String credentials;

    public AuthenticationCode() {
        super();
    }

    public AuthenticationCode(String principal, String credentials) {
        super();
        this.principal = principal;
        this.credentials = credentials;
    }

    /**
     * @return the principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * @param principal
     *            the principal to set
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * @return the credentials
     */
    public String getCredentials() {
        return credentials;
    }

    /**
     * @param credentials
     *            the credentials to set
     */
    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AuthenticationCode [principal=" + principal + ", credentials=" + credentials + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
        result = prime * result + ((principal == null) ? 0 : principal.hashCode());
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
        AuthenticationCode other = (AuthenticationCode) obj;
        if (credentials == null) {
            if (other.credentials != null)
                return false;
        } else if (!credentials.equals(other.credentials))
            return false;
        if (principal == null) {
            if (other.principal != null)
                return false;
        } else if (!principal.equals(other.principal))
            return false;
        return true;
    }

}
