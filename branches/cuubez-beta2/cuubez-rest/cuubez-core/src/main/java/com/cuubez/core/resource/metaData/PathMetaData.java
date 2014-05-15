package com.cuubez.core.resource.metaData;


import java.util.List;

public class PathMetaData {

    private List<PathVariableMetaData> pathVariables;
    private String template;
    private String tail;
    private boolean empty;
    private boolean rootPath;

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

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isRootPath() {
        return rootPath;
    }

    public void setRootPath(boolean rootPath) {
        this.rootPath = rootPath;
    }
}
