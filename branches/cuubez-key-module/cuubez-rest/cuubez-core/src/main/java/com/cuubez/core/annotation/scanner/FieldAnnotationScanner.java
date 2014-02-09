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
package com.cuubez.core.annotation.scanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.cuubez.core.annotation.context.FieldAnnotationClasses;
import com.cuubez.core.annotation.context.FieldAnnotationMetaData;
import com.cuubez.core.annotation.context.ServiceAnnotationClasses;
import com.cuubez.core.annotation.context.ServiceAnnotationMethodName;
import com.cuubez.core.context.FieldContext;
import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.exception.CuubezException;

public class FieldAnnotationScanner {

    public void scan(Class<?> clazz, ServiceRepository serviceRepository) throws CuubezException {

        Class<?> fieldType = null;
        String fieldName = null, annotationName = null, assignedFieldName = null;
        Field[] fields = clazz.getDeclaredFields();
        FieldAnnotationMetaData fieldAnnotationMetaData = new FieldAnnotationMetaData();
        boolean isEncryptField = false;

        for (Field field : fields) {

            annotationName = null;
            isEncryptField = false;
            assignedFieldName = null;

            if (field.getDeclaredAnnotations() != null && field.getDeclaredAnnotations().length > 0) {

                Annotation[] annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {

                    try {

                        assignedFieldName = (String) annotation.annotationType().getMethod(ServiceAnnotationMethodName.PARAMETER_NAME.value()).invoke(annotation);

                        if(annotation.annotationType().getName().equals(FieldAnnotationClasses.ENCRYPT_FIELD.fieldName())) {
                            isEncryptField = true;
                        }


                    } catch (IllegalArgumentException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (SecurityException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (IllegalAccessException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (InvocationTargetException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    } catch (NoSuchMethodException e) {
                        throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
                    }

                    annotationName = annotation.annotationType().getCanonicalName();
                    break;//only one annotation is allow to put on the field
                }

            }

            fieldName = field.getName();
            fieldType = field.getType();

            fieldAnnotationMetaData.addFieldMetaData(fieldName, assignedFieldName, annotationName, fieldType, isEncryptField);

        }
        FieldContext fieldContext = new FieldContext(fieldAnnotationMetaData);
        serviceRepository.addFieldDetails(clazz.getName(), fieldContext);

    }

}
