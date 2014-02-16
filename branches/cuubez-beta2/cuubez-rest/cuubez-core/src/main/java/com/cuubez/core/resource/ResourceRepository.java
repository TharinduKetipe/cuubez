package com.cuubez.core.resource;

import com.cuubez.core.exception.CuubezException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResourceRepository {
	
	private List<RootResource> rootResources = null;
    private static ResourceRepository instance = null;
	
	public static ResourceRepository getInstance() {

        if(instance == null) {
            instance = new ResourceRepository();
        }

        return instance;
    }

	private ResourceRepository() {
		super();
		this.rootResources = new LinkedList<RootResource>();
	}

	public void addRootResource(RootResource rootResource) {
        this.rootResources.add(rootResource);
	}


    public SelectedResourceMetaData findResource(String path, String httpMethod) throws CuubezException {

        if(rootResources == null || path == null || httpMethod == null) {
            throw new CuubezException("",CuubezException.SERVICE_NOT_FOUND); //TODO need change exception structure
        }

        for(RootResource rootResource : rootResources) {

            PathMetaData rootPathMetaData = rootResource.getUriTemplate().match(path);

            if(rootPathMetaData != null) {

                List<SubResource> subResources = rootResource.getSubResources();

                for (SubResource subResource : subResources) {

                    PathMetaData subPathMetaData = subResource.getUriTemplate().match(rootPathMetaData.getTail());

                    if(subPathMetaData != null && subResource.getMethodMetaData().getHttpMethod() != null && subResource.getMethodMetaData().getHttpMethod().toString().equals(httpMethod)) {

                        SelectedResourceMetaData selectedResourceMetaData = new SelectedResourceMetaData();
                        selectedResourceMetaData.setSelectedMethodMetaData(subResource.getMethodMetaData());
                        selectedResourceMetaData.addPathVariableMetaData(rootPathMetaData.getPathVariables());
                        selectedResourceMetaData.addPathVariableMetaData(subPathMetaData.getPathVariables());

                        return selectedResourceMetaData;
                    }
                }

            }
        }

        throw new CuubezException("",CuubezException.SERVICE_NOT_FOUND); //TODO need change exception structure
    }
	

}
