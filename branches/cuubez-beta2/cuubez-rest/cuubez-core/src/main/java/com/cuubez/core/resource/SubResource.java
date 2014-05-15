package com.cuubez.core.resource;

import com.cuubez.core.template.UriTemplate;
import com.cuubez.core.resource.metaData.MethodMetaData;

public class SubResource {

    private UriTemplate uriTemplate = null;
    private MethodMetaData methodMetaData = null;

    public MethodMetaData getMethodMetaData() {
        return methodMetaData;
    }

    public void setMethodMetaData(MethodMetaData methodMetaData) {
        this.methodMetaData = methodMetaData;
    }

    public UriTemplate getUriTemplate() {
        return uriTemplate;
    }

    public void setUriTemplate(UriTemplate uriTemplate) {
        this.uriTemplate = uriTemplate;
    }
}
