/**
 * 
 */
package com.cuubez.auth.exception;

/**
 * @author ruwan
 *
 */
public class AuthorizationConnectorException extends AuthorizationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5142871620900321041L;

	/**
	 * 
	 */
	public AuthorizationConnectorException() {

	}

	/**
	 * @param message
	 */
	public AuthorizationConnectorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AuthorizationConnectorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthorizationConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
