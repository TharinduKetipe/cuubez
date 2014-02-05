package com.cuubez.core.engine.uri.template;


import com.cuubez.core.resource.PathMetaData;
import java.util.regex.Pattern;

public abstract class UriTemplate {

    protected Pattern pattern = null;
    protected PathMetaData pathMetaData;

    public abstract PathMetaData match(String path);


    public void setTemplate(String template) {
        this.pattern = Pattern.compile(template);
    }

    public PathMetaData getPathMetaData() {
        return pathMetaData;
    }

    public void setPathMetaData(PathMetaData pathMetaData) {
        this.pathMetaData = pathMetaData;
    }
}
