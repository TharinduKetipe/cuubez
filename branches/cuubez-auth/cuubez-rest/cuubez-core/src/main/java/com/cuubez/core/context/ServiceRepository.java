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

    private Map<String, Map<String, ServiceContext>> services = new HashMap<String, Map<String, ServiceContext>>();
    private Map<String, FieldContext> fieldDetails = new HashMap<String, FieldContext>();
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

    public void addService(String httpMethod, String path, ServiceContext serviceContext) throws CuubezException {

        Map<String, ServiceContext> serviceContextMapByHttpMethod = services.get(httpMethod);

        if (serviceContextMapByHttpMethod == null) {
            serviceContextMapByHttpMethod = new HashMap<String, ServiceContext>();
            services.put(httpMethod, serviceContextMapByHttpMethod);
        }

        ServiceContext serviceContextByPath = serviceContextMapByHttpMethod.get(path);

        if (serviceContextByPath == null) {
            serviceContextMapByHttpMethod.put(path, serviceContext);
        } else {
            throw new CuubezException("Service [" + path + "] already exist in service repository", CuubezException.INTERNAL_EXCEPTION);
        }

    }

    public ServiceContext findService(String httpMethod, String path) throws CuubezException {

        Map<String, ServiceContext> serviceContextMapByHttpMethod = services.get(httpMethod);

        if (serviceContextMapByHttpMethod == null) {
            throw new CuubezException("Service  [" + path + "] not found", CuubezException.SERVICE_NOT_FOUND);
        }

        ServiceContext serviceContextByLocation = serviceContextMapByHttpMethod.get(path);

        if (serviceContextByLocation == null) {
            throw new CuubezException("Service [" + path + "] not found", CuubezException.SERVICE_NOT_FOUND);
        }

        return serviceContextByLocation;

    }

    public void addFieldDetails(String className, FieldContext fieldContext) {
        this.fieldDetails.put(className, fieldContext);
    }

    public FieldContext findFieldDetails(String className) {
        return this.fieldDetails.get(className);
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
