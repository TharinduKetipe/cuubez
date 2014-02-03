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
package com.cuubez.core.engine.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.core.context.MessageContext;

public class GzipCompressor implements Compressor {

    private static Log log = LogFactory.getLog(GzipCompressor.class);

    @Override
    public void compressAndWrite(HttpServletResponse response, MessageContext messageContext) {

        response.setHeader("Content-Encoding", "gzip");

        try {

            OutputStream out = response.getOutputStream();
            GZIPOutputStream gout = new GZIPOutputStream(out);
            gout.write(messageContext.getContent().getBytes());
            gout.close();
            out.close();

        } catch (IOException e) {
            log.error(e);
        }


    }


}
