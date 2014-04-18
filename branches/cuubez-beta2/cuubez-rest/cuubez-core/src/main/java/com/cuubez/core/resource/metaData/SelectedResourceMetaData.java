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


    public List<PathVariableMetaData> getPathVariableMetaDataList() {
        return pathVariableMetaDataList;
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

}
