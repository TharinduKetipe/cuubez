package com.cuubez.core.engine.uri;

import com.cuubez.core.resource.ClassMetaData;
import com.cuubez.core.resource.MethodMetaData;
import com.cuubez.core.engine.uri.template.UriTemplate;

public abstract class UriTemplateProcessor {

    public abstract UriTemplate compile(ClassMetaData classMetaData);

    public abstract UriTemplate compile(MethodMetaData methodMetaData);

}
