/**
 * 
 */
package com.cuubez.auth.exception;

/**
 * @author ruwan
 *
 */
public class UnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3635642325441721152L;

	/**
	 * 
	 */
	public UnauthorizedException() {

	}

	/**
	 * @param message
	 */
	public UnauthorizedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

}
