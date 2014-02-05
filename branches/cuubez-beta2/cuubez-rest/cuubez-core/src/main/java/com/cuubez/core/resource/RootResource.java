package com.cuubez.core.resource;

import java.util.List;

import com.cuubez.core.engine.uri.UriTemplateProcessor;
import com.cuubez.core.engine.uri.template.UriTemplate;

public class RootResource {

	private List<SubResource> subResources = null;
	private UriTemplate uriTemplate = null;
    private ClassMetaData classMetaData = null;

	public List<SubResource> getSubResources() {
		return subResources;
	}

	public void setSubResources(List<SubResource> subResources) {
		this.subResources = subResources;
	}

    public UriTemplate getUriTemplate() {
        return uriTemplate;
    }

    public void setUriTemplate(UriTemplate uriTemplate) {
        this.uriTemplate = uriTemplate;
    }

    public ClassMetaData getClassMetaData() {
        return classMetaData;
    }

    public void setClassMetaData(ClassMetaData classMetaData) {
        this.classMetaData = classMetaData;
    }
}
