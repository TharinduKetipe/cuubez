/**
 *	Copyright [2013] [www.cuubez.com]
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package com.cuubez.auth.exception;

/**
 * @author ruwan
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