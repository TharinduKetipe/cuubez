package com.cuubez.core.annotation.context;


public class ClassMetaData {

    private String path = null;

    public ClassMetaData(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
