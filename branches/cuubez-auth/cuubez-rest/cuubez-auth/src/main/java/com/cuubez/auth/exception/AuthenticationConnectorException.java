/**
 * 
 */
package com.cuubez.auth.exception;

/**
 * @author ruwan
 */
public class AuthenticationConnectorException extends AuthenticationException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -7913191789588765121L;

    /**
	 * 
	 */
    public AuthenticationConnectorException() {

    }

    /**
     * @param message
     */
    public AuthenticationConnectorException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AuthenticationConnectorException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthenticationConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

}
