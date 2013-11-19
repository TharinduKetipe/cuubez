/**
 * 
 */
package com.cuubez.auth.exception;

/**
 * @author ruwan
 *
 */
public class AccountingConnectorException extends AccountingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3195658055829860L;

	/**
	 * 
	 */
	public AccountingConnectorException() {
	}

	/**
	 * @param message
	 */
	public AccountingConnectorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AccountingConnectorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AccountingConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
