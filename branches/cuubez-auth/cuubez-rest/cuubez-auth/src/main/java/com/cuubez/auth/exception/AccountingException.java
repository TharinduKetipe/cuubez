/**
 * 
 */
package com.cuubez.auth.exception;

/**
 * @author ruwan
 */
public class AccountingException extends UnauthorizedException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6818893034576579473L;

    /**
	 * 
	 */
    public AccountingException() {

    }

    /**
     * @param message
     */
    public AccountingException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AccountingException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public AccountingException(String message, Throwable cause) {
        super(message, cause);
    }

}
