package com.cuubez.core.resource;


import com.cuubez.core.engine.uri.JaxRsUriTemplateProcessor;
import com.cuubez.core.engine.uri.UriTemplateProcessor;
import com.cuubez.core.engine.uri.template.UriTemplate;
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
                methodMetaData.setReturnType(method.getReturnType());

                if (methodMetaData != null) {
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
