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
