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
package com.cuubez.core.handler;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.exception.CuubezException;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;

public class ResponseNormalizerHandler implements ResponseHandler {


    public void handleResponse(MessageContext messageContext) throws CuubezException {

        populateResponseObjectValues(messageContext);

    }

    private void populateResponseObjectValues(MessageContext messageContext) {

        if(messageContext.getResponseContext().getReturnObject() != null && messageContext.getResponseContext().getReturnObject() instanceof Response) {

            Response response = (Response) messageContext.getResponseContext().getReturnObject();
            int responseCode = response.getStatus();
            messageContext.getResponseContext().setResponseCode(responseCode);

            Object entity = response.getEntity();

            if(entity == null) {
                entity = "";
            }
            messageContext.getResponseContext().setReturnObject(entity);


            if(isVariable(entity)) {
                messageContext.getResponseContext().setNeedToTransform(false);
            }

            populateMetaData(messageContext, response);

        }
    }


    private boolean isVariable(Object object) {

        if(object == null) {
            return false;
        }

        if(object.getClass().equals(String.class) || object.getClass().equals(Integer.class) || object.getClass().equals(Double.class) ||
                object.getClass().equals(Short.class) || object.getClass().equals(Long.class) || object.getClass().equals(Float.class) ||
                object.getClass().equals(Boolean.class) || object.getClass().equals(Character.class)) {
            return true;

        }

        return false;
    }

    private void populateMetaData(MessageContext messageContext, Response response) {

        MultivaluedMap<String, Object> metadata = response.getMetadata();

        if(metadata != null && metadata.size() > 0) {

            populateContentLocation(messageContext, metadata);
            populateCookies(messageContext, metadata);
            populateExpiresDate(messageContext, metadata);
            populateCacheControl(messageContext, metadata);
            populateHeaders(messageContext, metadata);
            populateLanguage(messageContext, metadata);
            populateLastModified(messageContext, metadata);
            populateLocation(messageContext, metadata);
            populateType(messageContext, metadata);
            populateSeeOther(messageContext, metadata);
            populateTemporaryRedirect(messageContext, metadata);
            populateNoContent(messageContext, metadata);
            populateServerError(messageContext, metadata);

        }



    }



    //return Response.ok().cacheControl(new CacheControl()).build(); //Cache-Control:
    //return Response.ok().header("header-name", "header-value").build();  //header-name: header-value
   // return Response.status(Response.Status.FORBIDDEN).language(new Locale("sinhala","srilanka")).build(); //Content-Language
    //return Response.ok().lastModified(new Date()).build();//Last-Modified
    //return Response.ok().location(new URI("www.cuubez.com")).build(); //error need to chk
    //return Response.ok().type(MediaType.APPLICATION_JSON_TYPE).build(); //Return Json content type if producer is anything
    //return Response.noContent().build(); //2-4 not content
    //return Response.seeOther(new URI("http://www.cuubez.com")).build();   //error need to inversigate
    //return Response.serverError().build(); //500 error response
    // return Response.temporaryRedirect(new URI("http://www.cuubez.com")).build(); //redirect to the url need to investigate


    private void populateContentLocation(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

        Object object = metadata.get("Content-Location");

        if(object != null) {

           ArrayList<URI> uri = (ArrayList<URI>) object;

            if(uri != null && uri.size() > 0) {
                URI uriInfo = uri.get(0);
                messageContext.getResponseContext().addHeaderValues("Content-Location", uriInfo.toString());
            }

        }

    }

    private void populateCookies(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

        Object object = metadata.get("Set-Cookie");

        if(object != null) {

            ArrayList<NewCookie> newCookies = (ArrayList<NewCookie>) object;
            NewCookie newCookie = newCookies.get(0);

            if(newCookie != null) {
                messageContext.getResponseContext().addHeaderValues("Set-Cookie", newCookie.getName() + "=" + newCookie.getValue() + ",Version=" + newCookie.getVersion());
            }

        }

    }

    private void populateExpiresDate(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateCacheControl(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }
    private void populateHeaders(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateLanguage(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateLastModified(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateLocation(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateType(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateSeeOther(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateTemporaryRedirect(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateNoContent(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

    private void populateServerError(MessageContext messageContext, MultivaluedMap<String, Object> metadata) {

    }

}
