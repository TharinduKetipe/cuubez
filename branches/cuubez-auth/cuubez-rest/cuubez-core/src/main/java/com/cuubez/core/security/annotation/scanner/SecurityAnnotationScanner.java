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
                            securityContext.addTagsToEncrypt(responseObject.getResponseObjectName(), fieldAnnotationMetaData);
                        }
                    }
                }
            }
        }

        configurationContext.setSecurityContext(securityContext);
    }

    private String extractClassName(String responseObject) {

        if (responseObject == null) {
            return null;
        }
        String[] details = responseObject.split("\\.");

        if (details.length < 0) {
            return null;
        }

        return details[details.length - 1];
    }


}
