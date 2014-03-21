package com.cuubez.core.resource.metaData;


import com.cuubez.core.resource.metaData.HeaderVariableMetaData;
import com.cuubez.core.resource.metaData.MethodMetaData;
import com.cuubez.core.resource.metaData.PathVariableMetaData;
import com.cuubez.core.resource.metaData.QueryVariableMetaData;

import java.util.ArrayList;
import java.util.List;

public class SelectedResourceMetaData {

    private MethodMetaData selectedMethodMetaData = null;
    private List<PathVariableMetaData> pathVariableMetaDataList = null;
    private List<QueryVariableMetaData> queryVariableMetaDataList = null;
    private List<HeaderVariableMetaData> headerVariableMetaDataList = null;


    public List<PathVariableMetaData> getPathVariableMetaDataList() {
        return pathVariableMetaDataList;
    }

    public void setPathVariableMetaDataList(List<PathVariableMetaData> pathVariableMetaDataList) {
        this.pathVariableMetaDataList = pathVariableMetaDataList;
    }

    public MethodMetaData getSelectedMethodMetaData() {
        return selectedMethodMetaData;
    }

    public void setSelectedMethodMetaData(MethodMetaData selectedMethodMetaData) {
        this.selectedMethodMetaData = selectedMethodMetaData;
    }

    public void addPathVariableMetaData(List<PathVariableMetaData> pathVariableMetaDataList) {

        if(this.pathVariableMetaDataList == null) {
            this.pathVariableMetaDataList = new ArrayList<PathVariableMetaData>();
        }

        this.pathVariableMetaDataList.addAll(pathVariableMetaDataList);

    }

    public List<HeaderVariableMetaData> getHeaderVariableMetaDataList() {
        return headerVariableMetaDataList;
    }

    public void addHeaderVariableMetaDataList(List<HeaderVariableMetaData> headerVariableMetaDataList) {

        if (this.headerVariableMetaDataList == null) {
            this.headerVariableMetaDataList = new ArrayList<HeaderVariableMetaData>();
        }

        this.headerVariableMetaDataList.addAll(headerVariableMetaDataList);

    }

    public List<QueryVariableMetaData> getQueryVariableMetaDataList() {
        return queryVariableMetaDataList;
    }

    public void addQueryVariableMetaDataList(List<QueryVariableMetaData> queryVariableMetaDataList) {


        if (this.queryVariableMetaDataList == null) {
            this.queryVariableMetaDataList = new ArrayList<QueryVariableMetaData>();
        }

        this.queryVariableMetaDataList.addAll(queryVariableMetaDataList);

    }

}
