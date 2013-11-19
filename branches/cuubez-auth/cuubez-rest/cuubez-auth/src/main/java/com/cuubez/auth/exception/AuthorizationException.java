/**
 * 
 */
package com.cuubez.auth.exception;

/**
 * @author ruwan
 */
public class AuthorizationException extends UnauthorizedException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 4625511994230116270L;

    /**
	 * 
	 */
    public AuthorizationException() {

    }

    /**
     * @param message
     */
    public AuthorizationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AuthorizationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

}
