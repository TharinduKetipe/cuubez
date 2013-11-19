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
package com.cuubez.core.annotation.scanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.cuubez.core.annotation.HttpMethod;
import com.cuubez.core.annotation.context.MethodAnnotationMetaData;
import com.cuubez.core.annotation.context.ServiceAnnotationMethodName;
import com.cuubez.core.context.MediaType;
import com.cuubez.core.context.ServiceContext;
import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.exception.CuubezException;

public class MethodAnnotationScanner {

    public void scan(Class<?> clazz, ServiceRepository serviceRepository) throws CuubezException {

        Method[] methods = clazz.getDeclaredMethods();

        if (methods != null) {

            for (Method method : methods) {

                if (method.getDeclaredAnnotations() != null && method.getDeclaredAnnotations().length > 0) {

                    Annotation[] annotations = method.getAnnotations();
                    populateServiceMetaData(clazz, serviceRepository, annotations, method);

                }


            }

        }

    }


    private void populateServiceMetaData(Class<?> serviceClass, ServiceRepository serviceRepository, Annotation[] methodAnnotations, Method method) throws CuubezException {

        if (methodAnnotations != null) {

            for (Annotation annotation : methodAnnotations) {

                if (serviceRepository.getServiceAnnotationNames().contains(annotation.annotationType().getName())) {

                    String serviceName, serviceLocation;
                    HttpMethod httpMethod;
                    MediaType mediaType;
                    boolean isSecure;
                    String[] userIds;
                    String[] roleIds; 

                    try {

                        serviceName = (String) annotation.annotationType().getMethod(ServiceAnnotationMethodName.SERVICE_NAME.value()).invoke(annotation);
                        serviceLocation = (String) annotation.annotationType().getMethod(ServiceAnnotationMethodName.SERVICE_PATH.value()).invoke(annotation);
                        httpMethod = (HttpMethod) annotation.annotationType().getMethod(ServiceAnnotationMethodName.HTTP_METHOD.value()).invoke(annotation);
                        mediaType = (MediaType) annotation.annotationType().getMethod(ServiceAnnotationMethodName.MEDIA_TYPE.value()).invoke(annotation);
                        isSecure = (Boolean)annotation.annotationType().getMethod(ServiceAnnotationMethodName.SECURE.value()).invoke(annotation);
                        userIds = (String[])annotation.annotationType().getMethod(ServiceAnnotationMethodName.USER_IDS.value()).invoke(annotation);
                        roleIds = (String[])annotation.annotationType().getMethod(ServiceAnnotationMethodName.ROLE_IDS.value()).invoke(annotation);

                        String annotationName = annotation.annotationType().getCanonicalName();
                        String packageName = annotation.annotationType().getPackage().getName();
                        String methodName = method.getName();
                        Class<?> methodReturnType = method.getReturnType();

                        ServiceContext serviceContext = new ServiceContext();
                        serviceContext.setPackageName(packageName);
                        serviceContext.setServiceClass(serviceClass);
                        serviceContext.setServiceName(serviceName);
                        serviceContext.setMediaType(mediaType);
                        serviceContext.setSecure(isSecure);
                        serviceContext.setUserIds(userIds);
                        serviceContext.setRoleIds(roleIds);
                        
                        MethodAnnotationMetaData methodAnnotationMetaData = serviceContext.addServiceAnnotationMetaData(annotationName, serviceLocation)
                                .addMethodAnnotationMetaData(methodName, methodReturnType);

                        if (method.getParameterTypes() != null && method.getParameterTypes().length > 0) {

                            String parameterName = null;
                            for (int i = 0; i < method.getParameterTypes().length; i++) {

                                if (method.getParameterAnnotations()[i] != null && method.getParameterAnnotations()[i].length > 0) {
                                    parameterName = (String) method.getParameterAnnotations()[i][0].annotationType().getMethod(ServiceAnnotationMethodName.PARAMETER_NAME.value()).invoke(method.getParameterAnnotations()[i][0]);
                                }

                                methodAnnotationMetaData.addMethodParameters(parameterName, method.getParameterTypes()[i]);
                            }
                        }

                        serviceRepository.addService(httpMethod.name(), serviceLocation, serviceName, serviceContext);
                        break; //Only one service annotation is allow for one method


                    } catch (IllegalArgumentException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (SecurityException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (IllegalAccessException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (InvocationTargetException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (NoSuchMethodException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    }

                }


            }


        }

    }


}
