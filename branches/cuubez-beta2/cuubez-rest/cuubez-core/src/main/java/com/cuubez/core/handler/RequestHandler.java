package com.cuubez.core.handler;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.exception.CuubezException;

public interface RequestHandler {

    public void handle(MessageContext messageContext) throws CuubezException;
}
