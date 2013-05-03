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
package com.cuubez.core.engine.executor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.core.annotation.context.MethodAnnotationMetaData;
import com.cuubez.core.annotation.context.MethodParameter;
import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.ServiceContext;
import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.exception.CuubezException;
import com.thoughtworks.xstream.core.util.Primitives;


public class ServiceExecutor {

    private static Log log = LogFactory.getLog(ServiceExecutor.class);

    public void execute(ConfigurationContext configurationContext) throws CuubezException {

        List<Object> parameterObjects = null;
        List<MethodParameter> methodParameters = null;
        ServiceContext serviceContext = null;

        try {

            serviceContext = findService(configurationContext);

        } catch (CuubezException e) {
            log.error(e.getDescription());
            throw e;
        }

        try {

            configurationContext.setServiceContext(serviceContext);
            MethodAnnotationMetaData methodAnnotationMetaData = serviceContext.getServiceAnnotationMetaData().getMethodAnnotationMetaData();

            if (methodAnnotationMetaData.getMethodParameters() != null) {
                methodParameters = methodAnnotationMetaData.getMethodParameters();
            } else {
                methodParameters = new ArrayList<MethodParameter>();
            }

            parameterObjects = configurationContext.getUrlContext().getParameters();
            List<Class<?>> requestParamTypes = getParameterType(parameterObjects);

            if (!validateParameter(methodParameters, requestParamTypes)) {
                log.error("Invalid parameters");
                throw new CuubezException("Invalid parameters", CuubezException.INVALIDE_PARAMETERS);
            }

            Class<?>[] paramsClazzes = new Class<?>[methodParameters.size()];
            Object[] parameters = new Object[methodParameters.size()];

            if (methodParameters != null && parameterObjects != null) {

                for (int i = 0; i < methodParameters.size(); i++) {

                    paramsClazzes[i] = methodParameters.get(i).getParameterType();
                    parameters[i] = parameterObjects.get(i);

                }

            }

            Class<?> cls = serviceContext.getServiceClass();
            Object obj = cls.newInstance();

            java.lang.reflect.Method method = cls.getDeclaredMethod(methodAnnotationMetaData.getMethodName(), paramsClazzes);
            Object returnObject = method.invoke(obj, parameters);

            MessageContext msgContext = new MessageContext();
            msgContext.setMediaType(configurationContext.getUrlContext().getMediaType());
            msgContext.setReturnObject(returnObject);
            configurationContext.setMessageContext(msgContext);

        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (SecurityException e) {
            log.error(e);
        } catch (NoSuchMethodException e) {
            log.error(e);
        } catch (IllegalArgumentException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }


    }


    private ServiceContext findService(ConfigurationContext configurationContext) throws CuubezException {

        String httpMethod = configurationContext.getUrlContext().getHttpMethods();
        String serviceLocation = configurationContext.getUrlContext().getServiceLocation();
        String serviceName = configurationContext.getUrlContext().getServiceName();
        return ServiceRepository.getInstance().findService(httpMethod, serviceLocation, serviceName);

    }


    private List<Class<?>> getParameterType(List<Object> parameterObjects) {

        if (parameterObjects == null || parameterObjects.size() == 0) {
            return Collections.emptyList();
        }

        List<Class<?>> parameterType = new ArrayList<Class<?>>();

        for (Object obj : parameterObjects) {
            parameterType.add(obj.getClass());
        }

        return parameterType;
    }


    private boolean validateParameter(List<MethodParameter> serviceParam, List<Class<?>> requestParam) {

        if (serviceParam == null && requestParam == null) {
            return true;
        }

        if (serviceParam == null || requestParam == null) {
            return false;
        }

        if (serviceParam.size() != requestParam.size()) {
            return false;
        }

        for (int i = 0; i < serviceParam.size(); i++) {

            if (!getType(serviceParam.get(i).getParameterType()).equals(requestParam.get(i))) {
                return false;
            }

        }
        return true;

    }

    public Class<?> getType(Class<?> clazz) {

        if (clazz.isPrimitive()) {
            return Primitives.box(clazz);
        }
        return clazz;
    }

}
