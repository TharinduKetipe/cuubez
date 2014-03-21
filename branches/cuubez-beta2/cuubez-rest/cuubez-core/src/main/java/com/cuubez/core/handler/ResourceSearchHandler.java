package com.cuubez.core.handler;

import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.handler.RequestHandler;
import com.cuubez.core.resource.ResourceRepository;
import com.cuubez.core.resource.metaData.SelectedResourceMetaData;

public class ResourceSearchHandler implements RequestHandler {

   /* public void handle(RequestContext requestContext) throws CuubezException {

        String httpMethod = requestContext.getHttpMethod();
        String path = requestContext.getUrlContext().getServiceLocation();
        SelectedResourceMetaData selectedResourceMetaData = ResourceRepository.getInstance().findResource(path, httpMethod);

    }
      */
    @Override
    public void handle(MessageContext messageContext) throws CuubezException {
        String httpMethod = messageContext.getRequestContext().getHttpMethod();
        String path = messageContext.getRequestContext().getUrlContext().getServiceLocation();
        messageContext.getRequestContext().setSelectedResource(ResourceRepository.getInstance().findResource(path, httpMethod));

    }
}
