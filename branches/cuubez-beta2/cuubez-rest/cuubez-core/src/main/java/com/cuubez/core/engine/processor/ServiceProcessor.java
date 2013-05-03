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
package com.cuubez.core.engine.processor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cuubez.core.annotation.scanner.AnnotationScanner;
import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.context.ExceptionContext;
import com.cuubez.core.context.MediaType;
import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.URLContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.engine.executor.ServiceExecutor;
import com.cuubez.core.initiator.param.HttpMethods;
import com.cuubez.core.engine.parser.Parser;
import com.cuubez.core.engine.parser.ParserFactory;

public class ServiceProcessor {

    private static Log log = LogFactory.getLog(ServiceProcessor.class);


    public MessageContext process(HttpServletRequest request, HttpMethods httpMethod) {

        ConfigurationContext configurationContext = new ConfigurationContext();

        try {

            populateValues(configurationContext, request, httpMethod);
            ParserFactory factory = new ParserFactory();
            factory.parse(configurationContext, configurationContext.getUrlContext().getMediaType(), Parser.URL);
            factory.parse(configurationContext, configurationContext.getUrlContext().getMediaType(), Parser.PARAMETER);
            ServiceExecutor serviceExecutor = new ServiceExecutor();
            serviceExecutor.execute(configurationContext);

            factory.parse(configurationContext, configurationContext.getUrlContext().getMediaType(), Parser.CONTENT);

        } catch (CuubezException e) {

            ExceptionContext exceptionContext = new ExceptionContext();
            exceptionContext.setThrowable(e);
            exceptionContext.setExceptionCode(e.getExceptionCode());
            configurationContext.setExceptionContext(exceptionContext);
            ParserFactory parserFactory = new ParserFactory();

            try {

                parserFactory.parse(configurationContext, requestMediaType(request.getContentType()), Parser.EXCEPTION);

            } catch (CuubezException err) {
                log.error(err);
            }

        }

        return configurationContext.getMessageContext();

    }

    public void initializeServiceRepository() throws CuubezException {

        AnnotationScanner scanner = new AnnotationScanner();
        scanner.scan();

    }


    private void populateValues(ConfigurationContext configurationContext, HttpServletRequest request, HttpMethods httpMethod) throws CuubezException {

        configurationContext.setRequest(request);

        URLContext urlContext = new URLContext();
        urlContext.setHttpMethods(httpMethod.name());
        urlContext.setMediaType(requestMediaType(request.getContentType()));
        configurationContext.setUrlContext(urlContext);

    }

    private MediaType requestMediaType(String requestContentType) {

        MediaType defaultMediaType = MediaType.XML;

        if (requestContentType == null) {
            return defaultMediaType;
        }

        for (MediaType mediaType : MediaType.values()) {
            if (requestContentType.contains(mediaType.getValue())) {
                return mediaType;
            }
        }

        return defaultMediaType;

    }

}
