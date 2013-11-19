package com.cuubez.core.annotation.scanner;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
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
import org.apache.commons.logging.LogFactory;

import com.cuubez.core.context.ApplicationConfigurationContext;
import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.exception.CuubezException;

public class AnnotationScanner {

    private static Log log = LogFactory.getLog(AnnotationScanner.class);

    public synchronized void scan() throws CuubezException {

        try {
            String applicationPath = ApplicationConfigurationContext.getInstance()
                    .getApplicationPath();
            ClassScanner scanner = new ClassScanner();
            List<Class<?>> classes = scanner.discover(applicationPath);

            ServiceRepository serviceRepository = ServiceRepository.getInstance();

            for (Class<?> clazz : classes) {
                new ClassAnnotationScanner().scan(clazz, serviceRepository);
                new MethodAnnotationScanner().scan(clazz, serviceRepository);
                new FieldAnnotationScanner().scan(clazz, serviceRepository);

            }

        } catch (CuubezException e) {
            log.error(e.getDescription(), e);
            throw e;
        } catch (IOException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        }

    }

}
