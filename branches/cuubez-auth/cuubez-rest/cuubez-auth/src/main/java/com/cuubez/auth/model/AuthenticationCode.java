/**
 * 
 */
package com.cuubez.auth.model;


/**
 * @author ruwan
 *
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
	 * @param principal the principal to set
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
	 * @param credentials the credentials to set
	 */
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	
}
