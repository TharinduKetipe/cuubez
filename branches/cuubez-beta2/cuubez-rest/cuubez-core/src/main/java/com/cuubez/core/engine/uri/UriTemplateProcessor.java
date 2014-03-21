package com.cuubez.core.engine.uri;

import com.cuubez.core.resource.metaData.ClassMetaData;
import com.cuubez.core.resource.metaData.MethodMetaData;
import com.cuubez.core.engine.uri.template.UriTemplate;

public abstract class UriTemplateProcessor {

    public abstract UriTemplate compile(ClassMetaData classMetaData);

    public abstract UriTemplate compile(MethodMetaData methodMetaData);

}
