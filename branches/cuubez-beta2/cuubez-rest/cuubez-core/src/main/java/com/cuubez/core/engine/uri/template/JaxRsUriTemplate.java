package com.cuubez.core.engine.uri.template;


import com.cuubez.core.engine.uri.UriTemplateBuilder;
import com.cuubez.core.resource.PathMetaData;
import com.cuubez.core.resource.PathVariableMetaData;

import java.util.List;
import java.util.regex.Matcher;

public class JaxRsUriTemplate extends UriTemplate {

    @Override
    public PathMetaData match(String path) {

        List<PathVariableMetaData> pathVariableMetaDataList = pathMetaData.getPathVariables();
        Matcher matcher = pattern.matcher(path);

        if(matcher.matches()) {

            for (int i = 0 ; i < pathVariableMetaDataList.size() ; i++) {
                pathVariableMetaDataList.get(i).setValue(matcher.group(i+3));
            }

            String tail = matcher.group(pathVariableMetaDataList.size() + 3);
            pathMetaData.setTail(tail);

          return pathMetaData;

        }
        return null;
    }
}
