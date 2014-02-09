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

package com.cuubez.core.security.utils;

import java.io.OutputStream;


public class StringOutputStream extends OutputStream {

    // This buffer will contain the stream
    protected StringBuffer buf = new StringBuffer();

    public StringOutputStream() {
    }

    public void close() {
    }

    public void flush() {

        // Clear the buffer
        buf.delete(0, buf.length());
    }

    public void write(byte[] b) {
        String str = new String(b);
        this.buf.append(str);
    }

    public void write(byte[] b, int off, int len) {
        String str = new String(b, off, len);
        this.buf.append(str);
    }

    public void write(int b) {
        String str = Integer.toString(b);
        this.buf.append(str);
    }

    public String toString() {
        return buf.toString();
    }
}
