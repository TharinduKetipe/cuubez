package com.cuubez.core.resource;

import java.lang.reflect.Method;
import java.util.List;


public class MethodMetaData {

	private Method reflectionMethod;
	private String path;
    private String[] consume;
	private String[] produce;
    private Class<?> clazz;
    private String httpMethod;
    private List<PathVariableMetaData> pathVariableMetaDataList;
    private List<QueryVariableMetaData> queryVariableMetaDataList;
    private List<HeaderVariableMetaData> headerVariableMetaDataList;
    private Class<?> returnType = null;


	public Method getReflectionMethod() {
		return reflectionMethod;
	}
	public void setReflectionMethod(Method reflectionMethod) {
		this.reflectionMethod = reflectionMethod;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

    public String[] getConsume() {
        return consume;
    }

    public void setConsume(String[] consume) {
        this.consume = consume;
    }

    public String[] getProduce() {
        return produce;
    }

    public void setProduce(String[] produce) {
        this.produce = produce;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<HeaderVariableMetaData> getHeaderVariableMetaDataList() {
        return headerVariableMetaDataList;
    }

    public void setHeaderVariableMetaDataList(List<HeaderVariableMetaData> headerVariableMetaDataList) {
        this.headerVariableMetaDataList = headerVariableMetaDataList;
    }

    public List<PathVariableMetaData> getPathVariableMetaDataList() {
        return pathVariableMetaDataList;
    }

    public void setPathVariableMetaDataList(List<PathVariableMetaData> pathVariableMetaDataList) {
        this.pathVariableMetaDataList = pathVariableMetaDataList;
    }

    public List<QueryVariableMetaData> getQueryVariableMetaDataList() {
        return queryVariableMetaDataList;
    }

    public void setQueryVariableMetaDataList(List<QueryVariableMetaData> queryVariableMetaDataList) {
        this.queryVariableMetaDataList = queryVariableMetaDataList;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
