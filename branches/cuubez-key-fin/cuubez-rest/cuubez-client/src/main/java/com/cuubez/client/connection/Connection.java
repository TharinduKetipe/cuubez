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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;

import com.cuubez.client.context.MessageContext;
import com.cuubez.client.context.RequestContext;
import com.cuubez.client.exception.CuubezException;

public abstract class Connection {

	private static Log log = LogFactory.getLog(Connection.class);

	public abstract void connect(MessageContext messageContext)
			throws CuubezException;

	public abstract void writeContent(MessageContext messageContext)
			throws CuubezException;

	protected HttpURLConnection getConnection(RequestContext requestContext)
			throws CuubezException {

		URL urlcon;
		HttpURLConnection conn = null;

		try {

			urlcon = new URL(requestContext.getServiceUrl());
			conn = (HttpURLConnection) urlcon.openConnection();

			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", requestContext
					.getMediaType().getValue().concat("; charset=utf-8"));
			conn.setRequestProperty("principal", requestContext.getPrincipal());
			conn.setRequestProperty("credentials",
					requestContext.getCredentials());
			conn.setRequestProperty("KeyExAlgoName",
					String.valueOf(requestContext.getKeyExAlgoName()));
			if (requestContext.getPublicKey() != null) {
				byte[] coded = Base64.encode(requestContext.getPublicKey());
				String strCoded = new String(coded);
				conn.setRequestProperty("PublicKey", strCoded);
			}
			if (requestContext.getPublicKey2() != null) {
				byte[] coded = Base64.encode(requestContext.getPublicKey2());
				String strCoded = new String(coded);
				conn.setRequestProperty("PublicKey2", strCoded);
			}
			conn.setRequestMethod(requestContext.getHttpMethod().name());
			conn.setUseCaches(false);
			conn.setDoOutput(true);

		} catch (ProtocolException e) {
			log.error("Connection Error");
			throw new CuubezException(e);
		} catch (MalformedURLException e) {
			log.error("Connection Error");
			throw new CuubezException(e);
		} catch (IOException e) {
			log.error("Connection Error");
			throw new CuubezException(e);
		}

		return conn;

	}

}
