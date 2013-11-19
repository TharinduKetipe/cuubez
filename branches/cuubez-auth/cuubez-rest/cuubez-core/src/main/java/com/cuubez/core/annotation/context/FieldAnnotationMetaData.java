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

import java.util.ArrayList;
import java.util.List;

public class FieldAnnotationMetaData {

    private List<FieldMetaData> fieldMetaData = null;

    public List<FieldMetaData> getFieldMetaData() {
        return fieldMetaData;
    }

    public void addFieldMetaData(String fieldName, String assignedFieldName,
                                 String annotationName, Class<?> fieldType) {
        if (this.fieldMetaData == null) {
            this.fieldMetaData = new ArrayList<FieldMetaData>();
        }

        FieldMetaData fieldMetaData = new FieldMetaData(fieldName, assignedFieldName, annotationName, fieldType);
        this.fieldMetaData.add(fieldMetaData);
    }


}
