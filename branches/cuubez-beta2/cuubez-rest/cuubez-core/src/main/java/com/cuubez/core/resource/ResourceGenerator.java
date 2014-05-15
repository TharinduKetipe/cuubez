package com.cuubez.core.resource;


import com.cuubez.core.template.JaxRsUriTemplateProcessor;
import com.cuubez.core.template.UriTemplateProcessor;
import com.cuubez.core.template.UriTemplate;
import com.cuubez.core.resource.metaData.ClassMetaData;
import com.cuubez.core.resource.metaData.MethodMetaData;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

public class ResourceGenerator {


  public RootResource generateResource(Class<?> clazz) {

      RootResource resource = null;

      if(ResourceMetaDataScanner.isResource(clazz)){

          resource = new RootResource();

          ResourceMetaDataScanner resourceMetaDataScanner = new ResourceMetaDataScanner();
          ClassMetaData classMetaData = resourceMetaDataScanner.scanClass(clazz);

          if(classMetaData == null) {
            return null;
          }

          resource.setClassMetaData(classMetaData);

          UriTemplateProcessor templateProcessor = new JaxRsUriTemplateProcessor();
          UriTemplate uriTemplate = templateProcessor.compile(classMetaData);
          resource.setUriTemplate(uriTemplate);

          resource.setSubResources(generateSubResource(resourceMetaDataScanner, clazz));

      }

      return resource;

  }


    private List<SubResource> generateSubResource(ResourceMetaDataScanner resourceMetaDataScanner, Class<?> clazz) {

        Method[] methods = clazz.getDeclaredMethods();

        List<SubResource> subResources = new ArrayList<SubResource>();

        for (Method method : methods) {

            if (ResourceMetaDataScanner.isSubResource(method)) {
                MethodMetaData methodMetaData = resourceMetaDataScanner.scanMethods(clazz, method);

                if (methodMetaData != null) {
                    methodMetaData.setReturnType(method.getReturnType());

                    SubResource subResource = new SubResource();
                    subResource.setMethodMetaData(methodMetaData);

                    UriTemplateProcessor templateProcessor = new JaxRsUriTemplateProcessor();
                    UriTemplate uriTemplate = templateProcessor.compile(methodMetaData);

                    subResource.setUriTemplate(uriTemplate);
                    subResources.add(subResource);

                }

            }
        }
        return subResources;
    }


}
