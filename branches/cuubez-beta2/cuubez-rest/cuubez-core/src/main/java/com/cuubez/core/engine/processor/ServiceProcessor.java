package com.cuubez.core.engine.processor;


import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.context.ResponseContext;
import com.cuubez.core.engine.executor.ServiceExecutor;
import com.cuubez.core.engine.transform.Transformer;
import com.cuubez.core.engine.transform.json.JSONTransformer;
import com.cuubez.core.engine.transform.xml.XMLTransformer;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.handler.RequestHandlerChain;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import javax.servlet.http.HttpServletRequest;

public class ServiceProcessor {

    public ResponseContext process(HttpServletRequest request, String httpMethod) {

        RequestContext requestContext = new RequestContext();
        RequestConfigurationContext requestConfigurationContext = new RequestConfigurationContext(httpMethod, request);
        RequestHandlerChain requestHandlerChain = new RequestHandlerChain();


        ResponseContext responseContext = null;

               try {

                   requestHandlerChain.doChain(requestContext, requestConfigurationContext);



               /*    populateValues(requestContext, request, httpMethod);


                   ParserFactory factory = new ParserFactory();
                   factory.parse(requestContext, Parser.URL);           */
                  // factory.parse(requestContext, Parser.PARAMETER);
                   ServiceExecutor serviceExecutor = new ServiceExecutor();

                   responseContext = serviceExecutor.execute(requestContext);

                   if(responseContext != null) {
                     transform(responseContext);
                   }

               }catch (CuubezException e) {

               }


      return responseContext;
    }


/*    private void populateValues(RequestContext requestContext, HttpServletRequest request, HttpMethod httpMethod) throws CuubezException {

            requestContext.setRequest(request);

            URLContext urlContext = new URLContext();
            urlContext.setHttpMethods(httpMethod.name());
            urlContext.setMediaType(request.getContentType());
            requestContext.setUrlContext(urlContext);

        }
             */


    private void transform(ResponseContext responseContext) {


        if(MediaType.APPLICATION_XML.equals(responseContext.getMediaType())) {

            Transformer transformer = new XMLTransformer();
            String output = transformer.marshal(responseContext.getReturnObject());
            responseContext.setContent(output);

        } else if(MediaType.APPLICATION_JSON.equals(responseContext.getMediaType())) {

            Transformer transformer = new JSONTransformer();
            String output = transformer.marshal(responseContext.getReturnObject());
            responseContext.setContent(output);

        }

    }


}
