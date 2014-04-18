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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.RequestConfigurationContext;
import com.cuubez.core.context.RequestContext;
import com.cuubez.core.context.ResponseContext;

import javax.ws.rs.core.MediaType;

import com.cuubez.core.resource.ResourceRepository;
import com.cuubez.core.resource.metaData.SelectedResourceMetaData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.core.exception.CuubezException;



public class ResourceInvokeHandler implements RequestHandler {

    private static Log log = LogFactory.getLog(ResourceInvokeHandler.class);


    @Override
    public void handleRequest(MessageContext messageContext) throws CuubezException {

        SelectedResourceMetaData selectedResourceMetaData = messageContext.getRequestContext().getSelectedResource();

        try {

            Object[] arguments = getResourceArguments(selectedResourceMetaData);
            Class<?> cls = selectedResourceMetaData.getSelectedMethodMetaData().getClazz();
            Object obj = cls.newInstance();
            java.lang.reflect.Method selectedMethod = selectedResourceMetaData.getSelectedMethodMetaData().getReflectionMethod();
            validateArguments(selectedMethod, arguments);
            Object returnObject = selectedMethod.invoke(obj, arguments);

            if (selectedMethod.getReturnType() != null && returnObject != null) {
                messageContext.getResponseContext().setReturnObject(returnObject);
                messageContext.getResponseContext().setMediaType(populateMediaType(selectedResourceMetaData.getSelectedMethodMetaData().getProduce(), messageContext.getRequestContext().getUrlContext().getMediaType()));

            }


        } catch (InvocationTargetException e) {
            log.error(e);
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (SecurityException e) {
            log.error(e);
        } catch (IllegalArgumentException e) {
            log.error(e);
        }

    }

    private boolean validateArguments(java.lang.reflect.Method selectedMethod, Object[] arguments) {
        return true;
    }

    private Object[] getResourceArguments(SelectedResourceMetaData selectedResourceMetaData) {
        return selectedResourceMetaData.getSelectedMethodMetaData().getParameters();
    }

    private String populateMediaType(String[] produceMediaTypes, String requestMediaType) throws CuubezException {

        if (requestMediaType == null && isNotSpecifyMediaType(produceMediaTypes)) {

            return MediaType.APPLICATION_XML;

        } else if (requestMediaType == null && !isNotSpecifyMediaType(produceMediaTypes)) {

            return produceMediaTypes[0];

        } else if (requestMediaType != null && !isNotSpecifyMediaType(produceMediaTypes)) {

            for (String produceMediaType : produceMediaTypes) {

                if (requestMediaType.equals(produceMediaType)) {
                    return requestMediaType;
                }
            }

        } else {
            throw new CuubezException("Unsupported MediaTYpe", 1);
        }

        throw new CuubezException("Unsupported MediaTYpe", 1);
    }


    private boolean isNotSpecifyMediaType(String[] produce) {

        if (produce == null || produce.length == 0 || (produce.length == 1 && produce[0] == "*/*")) {

            return true;

        }

        return false;

    }


}
