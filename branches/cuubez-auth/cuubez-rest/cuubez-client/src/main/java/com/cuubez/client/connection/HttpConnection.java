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
package com.cuubez.client.connection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.client.context.MessageContext;
import com.cuubez.client.exception.CuubezException;

public class HttpConnection extends Connection {

	private static Log log = LogFactory.getLog(HttpConnection.class);	
	
	public void connect(MessageContext messageContext) throws CuubezException {
		
		HttpURLConnection httpURLConnection = null;
		try {
			httpURLConnection = getConnection(messageContext.getRequestContext());
		} catch (CuubezException e) {
			log.error("Connection Error");
			throw new CuubezException(e);
		}
		messageContext.getRequestContext().setHttpURLConnection(httpURLConnection);
		
	}
	
	
	public void writeContent(MessageContext messageContext) throws CuubezException  {
		
		HttpURLConnection httpConnection = null;
		OutputStream out = null;
		GZIPOutputStream gout = null;
		
		try {
		String parameters = messageContext.getRequestContext().getTransformedParameter();
		
		if(parameters != null) {
			
			httpConnection = messageContext.getRequestContext().getHttpURLConnection();
			httpConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			out = httpConnection.getOutputStream();
			gout = new GZIPOutputStream(out);
			gout.write(parameters.getBytes());
		
			}
		
		} catch(IOException e) {
			
			log.error("Http Connection exception");
			throw new CuubezException(e);
			
		} finally {
			try {
				
				if(gout != null) {
					gout.close();
				}
				if(out != null) {
					out.close();
				}
			
			} catch(IOException e) {
				log.error(e);
			}
			
			httpConnection.disconnect();
		}
		
	}

}
