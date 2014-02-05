package com.cuubez.core.engine.uri;


import com.cuubez.core.resource.PathMetaData;
import com.cuubez.core.resource.PathVariableMetaData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriTemplateBuilder {

    public static String VARIABLE = "([^/]+?)";
    public static String TAIL = "((?:/.*)?)";
    public static String VARIABLE_START = "{";
    public static String VARIABLE_END = "}";
    public static String TEMPLATE_START = "(";
    public static String TEMPLATE_END = ")";
    public static final Pattern variablePattern = Pattern.compile("\\{[ \\t]*(\\w[\\w\\.-]*)[ \\t]*(?::[ \\t]*((?:(?:[^{}])|(?:\\{[^{}]*\\}))*)[ \\t]*)?\\}");


    public PathMetaData build(String path) {

        List<PathVariableMetaData> pathVariableMetaData = extractVariables(path);
        List<String> pathVariableNames = populateVariableNames(pathVariableMetaData);

        StringBuffer uriTemplate = new StringBuffer();
        uriTemplate.append(TEMPLATE_START);
        uriTemplate.append(TEMPLATE_START);
        uriTemplate.append(path);
        uriTemplate.append(TEMPLATE_END);

        for (String pathVariableName : pathVariableNames) {

            String variableMatcher = VARIABLE_START + pathVariableName + VARIABLE_END;
            int startIndex = uriTemplate.indexOf(variableMatcher);
            int lastIndex = startIndex + variableMatcher.length();

            uriTemplate.replace(startIndex, lastIndex, VARIABLE);


        }

        uriTemplate.append(TAIL);
        uriTemplate.append(TEMPLATE_END);

        PathMetaData pathMetaData = new PathMetaData(pathVariableMetaData, uriTemplate.toString());

        return pathMetaData;

    }


    private List<PathVariableMetaData> extractVariables(String path) {

        List<PathVariableMetaData> pathVariables = new ArrayList<PathVariableMetaData>();
        Matcher variableMatcher = variablePattern.matcher(path);

        while (variableMatcher.find()) {

            PathVariableMetaData pathVariable = new PathVariableMetaData(variableMatcher.group(1), null);
            pathVariables.add(pathVariable);

        }

        return pathVariables;

    }

    public static List<String> populateVariableNames(List<PathVariableMetaData> pathVariableMetaDatas) {

        if(pathVariableMetaDatas.isEmpty()) {
            Collections.emptyList();
        }

        List<String> pathVariableNames = new ArrayList<String>();
        for (PathVariableMetaData pathVariableMetaData : pathVariableMetaDatas) {
            pathVariableNames.add(pathVariableMetaData.getName());
        }

        return pathVariableNames;
    }


}
