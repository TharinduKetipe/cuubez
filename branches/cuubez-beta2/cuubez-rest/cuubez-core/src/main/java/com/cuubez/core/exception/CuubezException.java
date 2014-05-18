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
package com.cuubez.core.exception;

public class CuubezException extends Exception implements CuubezExceptionConstance {

    private static final long serialVersionUID = 6889728891031940898L;

    private String message;
    private Throwable nestedThrowable = null;

    public CuubezException(String message) {
        super();
        this.message = message;
    }

    public CuubezException(Throwable throwable) {
        super();
        this.nestedThrowable = throwable;
    }

    public CuubezException(String description, Throwable throwable) {
        super();
        this.message = description;
        this.nestedThrowable = throwable;
    }

    public String getMessage() {
        return this.message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Throwable getNestedThrowable() {
        return nestedThrowable;
    }


    public void setNestedThrowable(Throwable nestedThrowable) {
        this.nestedThrowable = nestedThrowable;
    }


}
