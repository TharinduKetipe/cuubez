package com.cuubez.core.handler;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.transform.Transformer;
import com.cuubez.core.transform.json.JSONTransformer;
import com.cuubez.core.transform.xml.XMLTransformer;

import javax.ws.rs.core.MediaType;

public class ResponseTransformHandler implements ResponseHandler {


    @Override
    public void handleResponse(MessageContext messageContext) {


        if (MediaType.APPLICATION_XML.equals(messageContext.getResponseContext().getMediaType())) {

            Transformer transformer = new XMLTransformer();
            String output = transformer.marshal(messageContext.getResponseContext().getReturnObject());
            messageContext.getResponseContext().setContent(output);

        } else if (MediaType.APPLICATION_JSON.equals(messageContext.getResponseContext().getMediaType())) {

            Transformer transformer = new JSONTransformer();
            String output = transformer.marshal(messageContext.getResponseContext().getReturnObject());
            messageContext.getResponseContext().setContent(output);

        }

    }
}
