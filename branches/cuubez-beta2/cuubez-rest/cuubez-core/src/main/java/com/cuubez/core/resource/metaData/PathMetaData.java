package com.cuubez.core.resource.metaData;


import java.util.List;

public class PathMetaData {

    private List<PathVariableMetaData> pathVariables;
    private String template;
    private String tail;

    public PathMetaData(List<PathVariableMetaData> pathVariables, String template) {
        this.pathVariables = pathVariables;
        this.template = template;
    }

    public List<PathVariableMetaData> getPathVariables() {
        return pathVariables;
    }

    public void setPathVariables(List<PathVariableMetaData> pathVariables) {
        this.pathVariables = pathVariables;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }
}
