/**
 * 
 */
package com.cuubez.auth.exception;

/**
 * @author ruwan
 *
 */
public class AuthenticationException extends UnauthorizedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2518297525109148423L;

	/**
	 * 
	 */
	public AuthenticationException() {

	}

	/**
	 * @param message
	 */
	public AuthenticationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AuthenticationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

}
