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

import com.cuubez.core.annotation.ApplicationPath;
import com.cuubez.core.annotation.RestService;
import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.exception.CuubezException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ClassAnnotationScanner implements AnnotationScanner {



    public void scan(Class<?> clazz, ServiceRepository serviceRepository) throws CuubezException {

        if (!clazz.isInterface()) {
            Annotation[] annotations = clazz.getDeclaredAnnotations();

            if (annotations != null) {

                for (Annotation annotation : annotations) {

                    if (ApplicationPath.class.getName().equals(annotation.annotationType().getName())) {
                        //  populateServiceMetaData(clazz, serviceRepository, annotation, method);
                        populateApplicationPath();
                    }
                }

            }

        }

    }


    private void populateApplicationPath() {



    }


}
