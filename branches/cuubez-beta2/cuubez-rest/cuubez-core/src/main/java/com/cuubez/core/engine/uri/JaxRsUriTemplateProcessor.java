package com.cuubez.core.engine.uri;

import com.cuubez.core.engine.uri.template.JaxRsUriTemplate;
import com.cuubez.core.resource.metaData.ClassMetaData;
import com.cuubez.core.resource.metaData.MethodMetaData;
import com.cuubez.core.resource.metaData.PathMetaData;
import com.cuubez.core.engine.uri.template.UriTemplate;

public class JaxRsUriTemplateProcessor extends UriTemplateProcessor {


    @Override
    public UriTemplate compile(ClassMetaData classMetaData) {

        UriTemplateBuilder uriTemplateBuilder = new UriTemplateBuilder();
        PathMetaData pathMetaData = uriTemplateBuilder.build(classMetaData.getPath());

        UriTemplate uriTemplate = new JaxRsUriTemplate();
        uriTemplate.setTemplate(pathMetaData.getTemplate());
        uriTemplate.setPathMetaData(pathMetaData);

        return uriTemplate;
    }

    @Override
    public UriTemplate compile(MethodMetaData methodMetaData) {

        if(methodMetaData.getPath() == null ) {
            return null;
        }

        UriTemplateBuilder uriTemplateBuilder = new UriTemplateBuilder();
        PathMetaData pathMetaData = uriTemplateBuilder.build(methodMetaData.getPath());

        UriTemplate uriTemplate = new JaxRsUriTemplate();
        uriTemplate.setTemplate(pathMetaData.getTemplate());
        uriTemplate.setPathMetaData(pathMetaData);

        return uriTemplate;
    }


}
