/**
 *	Copyright [2013] [www.cuubez.com]
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package com.cuubez.core.annotation.context;

public class FieldMetaData {

    private String fieldName = null;
    private String assignedFieldName = null;
    private String annotationName = null;
    private Class<?> fieldType = null;
    private boolean encrypt = false;

    public FieldMetaData(String fieldName, String assignedFieldName,
                         String annotationName, Class<?> fieldType, boolean encrypt) {
        super();
        this.fieldName = fieldName;
        this.assignedFieldName = assignedFieldName;
        this.annotationName = annotationName;
        this.fieldType = fieldType;
        this.encrypt = encrypt;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getAssignedFieldName() {
        return assignedFieldName;
    }

    public void setAssignedFieldName(String assignedFieldName) {
        this.assignedFieldName = assignedFieldName;
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public void setAnnotationName(String annotationName) {
        this.annotationName = annotationName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }
}
