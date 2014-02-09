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

package com.cuubez.core.security.annotation.scanner;


import com.cuubez.core.annotation.context.FieldAnnotationMetaData;
import com.cuubez.core.annotation.context.FieldMetaData;
import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.context.FieldContext;
import com.cuubez.core.context.ResponseObject;
import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.security.context.SecurityContext;

import java.util.Set;

public class SecurityAnnotationScanner {

    public void scanEncryptProperty(ConfigurationContext configurationContext) {

        Set<ResponseObject> responseObjects = configurationContext.getMessageContext().getResponseObjectList();
        SecurityContext securityContext = null;

        if (responseObjects != null) {

            securityContext = new SecurityContext();
            for (ResponseObject responseObject : responseObjects) {

                FieldAnnotationMetaData fieldAnnotationMetaData = null;
                FieldContext fieldContext = ServiceRepository.getInstance().findFieldDetails(responseObject.getResponseObjectName());

                if (fieldContext != null) {
                    fieldAnnotationMetaData = fieldContext.getFieldAnnotationMetaData();
                }

                if (fieldAnnotationMetaData != null && fieldAnnotationMetaData.getFieldMetaData() != null) {

                    for (FieldMetaData fieldMetaData : fieldAnnotationMetaData.getFieldMetaData()) {

                        if (fieldMetaData.isEncrypt()) {
                            securityContext.addTagsToEncrypt(fieldMetaData.getFieldName(), fieldAnnotationMetaData);
                        }
                    }
                }
            }
        }

        configurationContext.setSecurityContext(securityContext);
    }


}
