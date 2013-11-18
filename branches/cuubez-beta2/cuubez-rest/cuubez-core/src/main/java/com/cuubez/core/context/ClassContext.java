package com.cuubez.core.context;


import com.cuubez.core.annotation.context.ClassAnnotationMetaData;

import java.util.ArrayList;
import java.util.List;

public class ClassContext {

    private List<ClassAnnotationMetaData> classAnnotationMetaDetails = null;

    public List<ClassAnnotationMetaData> getClassAnnotationMetaDetails() {
        return classAnnotationMetaDetails;
    }

    public void addClassAnnotationMetaDetails(ClassAnnotationMetaData classAnnotationMetaData) {
        this.classAnnotationMetaDetails.add(classAnnotationMetaData);
    }
}
