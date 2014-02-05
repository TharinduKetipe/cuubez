package com.cuubez.core.engine.processor;


import com.cuubez.core.context.RequestContext;
import com.cuubez.core.context.ResponseContext;
import com.cuubez.core.context.URLContext;
import com.cuubez.core.engine.executor.ServiceExecutor;
import com.cuubez.core.engine.parser.Parser;
import com.cuubez.core.engine.parser.ParserFactory;
import com.cuubez.core.engine.transform.Transformer;
import com.cuubez.core.engine.transform.xml.XMLTransformer;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.util.HttpMethod;
import com.cuubez.core.util.MediaType;

import javax.servlet.http.HttpServletRequest;

public class ServiceProcessor {

    public ResponseContext process(HttpServletRequest request, HttpMethod httpMethod) {

        RequestContext requestContext = new RequestContext();
        ResponseContext responseContext = null;

               try {

                   populateValues(requestContext, request, httpMethod);
                   ParserFactory factory = new ParserFactory();
                   factory.parse(requestContext, Parser.URL);
                  // factory.parse(requestContext, Parser.PARAMETER);
                   ServiceExecutor serviceExecutor = new ServiceExecutor();
                   responseContext = serviceExecutor.execute(requestContext);

               }catch (CuubezException e) {

               }


      return responseContext;
    }


    private void populateValues(RequestContext requestContext, HttpServletRequest request, HttpMethod httpMethod) throws CuubezException {

            requestContext.setRequest(request);

            URLContext urlContext = new URLContext();
            urlContext.setHttpMethods(httpMethod.name());
            urlContext.setMediaType(requestMediaType(request.getContentType()));
            requestContext.setUrlContext(urlContext);

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


    private void transform(ResponseContext responseContext) {

        if(MediaType.XML.equals(responseContext.getMediaType())) {

            Transformer transformer = new XMLTransformer();
            String output = transformer.marshal(responseContext.getReturnObject());
            responseContext.setContent(output);
        }

    }

}
