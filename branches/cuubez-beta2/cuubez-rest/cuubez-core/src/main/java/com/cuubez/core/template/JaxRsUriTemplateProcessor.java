package com.cuubez.core.template;

import com.cuubez.core.resource.metaData.ClassMetaData;
import com.cuubez.core.resource.metaData.MethodMetaData;
import com.cuubez.core.resource.metaData.PathMetaData;

public class JaxRsUriTemplateProcessor extends UriTemplateProcessor {


    @Override
    public UriTemplate compile(ClassMetaData classMetaData) {

        UriTemplateBuilder uriTemplateBuilder = new UriTemplateBuilder();
        PathMetaData pathMetaData = uriTemplateBuilder.build(classMetaData.getPath(), true);

        UriTemplate uriTemplate = new JaxRsUriTemplate();
        uriTemplate.setTemplate(pathMetaData.getTemplate());
        uriTemplate.setPathMetaData(pathMetaData);

        return uriTemplate;
    }

    @Override
    public UriTemplate compile(MethodMetaData methodMetaData) {

        UriTemplateBuilder uriTemplateBuilder = new UriTemplateBuilder();
        PathMetaData pathMetaData = uriTemplateBuilder.build(methodMetaData.getPath(), false);

        UriTemplate uriTemplate = new JaxRsUriTemplate();
        uriTemplate.setTemplate(pathMetaData.getTemplate());
        uriTemplate.setPathMetaData(pathMetaData);

        return uriTemplate;
    }


}
