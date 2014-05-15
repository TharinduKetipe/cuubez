package com.cuubez.core.template;


import com.cuubez.core.resource.metaData.PathMetaData;
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
