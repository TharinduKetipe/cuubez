package com.cuubez.core.initiator;


import com.cuubez.core.context.ApplicationConfigurationContext;
import com.cuubez.core.resource.ResourceGenerator;
import com.cuubez.core.resource.ResourceRepository;
import com.cuubez.core.resource.RootResource;
import com.cuubez.core.scanner.file.ClassScanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;

public class ServiceRepositoryInitiator {

    private static Log log = LogFactory.getLog(ServiceRepositoryInitiator.class);

    public void initiate() {

        List<Class<?>> classes = null;

        String applicationPath = ApplicationConfigurationContext.getInstance().getApplicationPath();
        ClassScanner scanner = new ClassScanner();

        try {
            classes = scanner.discover(applicationPath);
        } catch (IOException e) {
            log.error(e);
        }

        if (classes != null) {

            for (Class<?> clazz : classes) {
                ResourceGenerator resourceGenerator = new ResourceGenerator();
                RootResource rootResource = resourceGenerator.generateResource(clazz);

                if(rootResource != null) {
                    ResourceRepository.getInstance().addRootResource(rootResource);
                }
            }
        }

    }


}
