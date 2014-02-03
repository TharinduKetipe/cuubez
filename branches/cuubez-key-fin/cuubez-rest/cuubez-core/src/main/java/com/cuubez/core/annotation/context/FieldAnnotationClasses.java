package com.cuubez.core.annotation.context;


public enum FieldAnnotationClasses {

    FIELD("com.cuubez.core.annotation.Field"),
    ENCRYPT_FIELD("com.cuubez.core.security.annotation.EncryptedField");

  private String fieldName;

    FieldAnnotationClasses(String fieldName) {
        this.fieldName = fieldName;
    }

    public String fieldName() {
        return fieldName;
    }
}
