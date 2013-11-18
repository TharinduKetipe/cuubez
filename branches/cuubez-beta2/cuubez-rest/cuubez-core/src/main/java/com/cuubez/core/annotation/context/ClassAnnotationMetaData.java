package com.cuubez.core.annotation.context;


import java.util.ArrayList;
import java.util.List;

public class ClassAnnotationMetaData {

    private List<ClassMetaData> classMetaData = null;

    public List<ClassMetaData> getClassMetaData() {
        return classMetaData;
    }

    public void addClassMetaData(String path) {

        if(this.classMetaData == null) {
            this.classMetaData = new ArrayList<ClassMetaData>();
        }

        ClassMetaData classMetaData = new ClassMetaData(path);
        this.classMetaData.add(classMetaData);
    }
}
