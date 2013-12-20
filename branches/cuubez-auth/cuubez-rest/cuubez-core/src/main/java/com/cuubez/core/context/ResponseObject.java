package com.cuubez.core.context;


public class ResponseObject {

    private String responseObjectName;
    private String responseObjectVariableName;

    public ResponseObject(String responseObject, String responseObjectVariableName) {
        this.responseObjectName = responseObject;
        this.responseObjectVariableName = responseObjectVariableName;
    }

    public String getResponseObjectName() {
        return responseObjectName;
    }

    public void setResponseObjectName(String responseObjectName) {
        this.responseObjectName = responseObjectName;
    }

    public String getResponseObjectVariableName() {
        return responseObjectVariableName;
    }

    public void setResponseObjectVariableName(String responseObjectVariableName) {
        this.responseObjectVariableName = responseObjectVariableName;
    }
}
