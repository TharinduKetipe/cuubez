/**
 * 
 */
package com.cuubez.auth.model;

/**
 * @author ruwan
 *
 */
public class AuthenticationToken {
	
	private String authToken;


	public AuthenticationToken(String authToken) {
		super();
		this.authToken = authToken;
	}
		
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
}
