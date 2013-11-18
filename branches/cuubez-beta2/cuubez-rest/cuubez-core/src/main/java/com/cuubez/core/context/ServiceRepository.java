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
package com.cuubez.core.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cuubez.core.annotation.context.ServiceAnnotationClasses;
import com.cuubez.core.exception.CuubezException;


public class ServiceRepository {

    private static ServiceRepository serviceRepository = null;

    private Map<String, Map<String, Map<String, ServiceContext>>> services = new HashMap<String, Map<String, Map<String, ServiceContext>>>();
    private Map<String, FieldContext> fieldDetails = new HashMap<String, FieldContext>();
    private Map<String, ClassContext> classDetails = new HashMap<String, ClassContext>();
    private List<String> serviceAnnotationNames = null;

    private ServiceRepository() {
    }

    public static synchronized ServiceRepository getInstance() {

        if (serviceRepository == null) {

            serviceRepository = new ServiceRepository();
            serviceRepository.populateAnnotationClass();
        }

        return serviceRepository;
    }

    public void addService(String httpMethod, String serviceLocation, String serviceName, ServiceContext serviceContext) throws CuubezException {

        Map<String, Map<String, ServiceContext>> serviceContextMapByHttpMethod = services.get(httpMethod);

        if (serviceContextMapByHttpMethod == null) {
            serviceContextMapByHttpMethod = new HashMap<String, Map<String, ServiceContext>>();
            services.put(httpMethod, serviceContextMapByHttpMethod);
        }

        Map<String, ServiceContext> serviceContextMap = serviceContextMapByHttpMethod.get(serviceLocation);

        if (serviceContextMap == null) {
            serviceContextMap = new HashMap<String, ServiceContext>();
            serviceContextMapByHttpMethod.put(serviceLocation, serviceContextMap);
        }

        ServiceContext serContext = serviceContextMap.get(serviceName);

        if (serContext != null) {
            throw new CuubezException("Service [" + serviceName + "] already exist in service repository", CuubezException.INTERNAL_EXCEPTION);
        }

        serviceContextMap.put(serviceName, serviceContext);

    }

    public ServiceContext findService(String httpMethod, String serviceLocation, String serviceName) throws CuubezException {

        Map<String, Map<String, ServiceContext>> serviceContextMapByHttpMethod = services.get(httpMethod);

        if (serviceContextMapByHttpMethod == null) {
            throw new CuubezException("Service  [" + serviceName + "] not found", CuubezException.SERVICE_NOT_FOUND);
        }

        Map<String, ServiceContext> serviceContextByLocation = serviceContextMapByHttpMethod.get(serviceLocation);

        if (serviceContextByLocation == null) {
            throw new CuubezException("Service [" + serviceName + "] not found", CuubezException.SERVICE_NOT_FOUND);
        }

        ServiceContext serviceContext = serviceContextByLocation.get(serviceName);

        if (serviceContext == null) {
            throw new CuubezException("Service [" + serviceName + "] not found", CuubezException.SERVICE_NOT_FOUND);
        }

        return serviceContext;

    }

    public void addFieldDetails(String className, FieldContext fieldContext) {
        this.fieldDetails.put(className, fieldContext);
    }

    public FieldContext findFieldDetails(String className) {
        return this.fieldDetails.get(className);
    }

    public ClassContext findClassDetails(String className) {
        return this.classDetails.get(className);
    }

    public void addClassDetails(String className, ClassContext classContext) {
        this.classDetails.put(className, classContext);
    }

    public List<String> getServiceAnnotationNames() {
        return serviceAnnotationNames;
    }

    private void populateAnnotationClass() {

        this.serviceAnnotationNames = new ArrayList<String>();

        for (ServiceAnnotationClasses annotationClass : ServiceAnnotationClasses.values()) {
            serviceAnnotationNames.add(annotationClass.serviceName());
        }

    }

}
