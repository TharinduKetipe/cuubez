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

package com.cuubez.core.security.process;


import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.context.MessageContext;
import com.cuubez.core.context.ServiceContext;
import com.cuubez.core.security.context.SecurityContext;
import com.cuubez.core.security.encrypt.DocumentEncryptProcessor;
import com.cuubez.core.security.sign.DocumentSignProcessor;

public class SecurityProcessor {

    public void process(ConfigurationContext configurationContext) throws Exception {

        MessageContext messageContext = configurationContext.getMessageContext();
        SecurityContext securityContext = configurationContext.getSecurityContext();
        ServiceContext serviceContext = configurationContext.getServiceContext();

        if (messageContext != null && securityContext != null) {

            if (serviceContext.isEncrypt() && serviceContext.isSign()) {

                new DocumentSignProcessor().sign(messageContext);
                new DocumentEncryptProcessor().encrypt(messageContext, securityContext, true);

            } else if (serviceContext.isEncrypt()) {

                new DocumentEncryptProcessor().encrypt(messageContext, securityContext, true);

            } else if (serviceContext.isSign() && securityContext.isPropertyLevelEncryption()) {

                new DocumentSignProcessor().sign(messageContext);
                new DocumentEncryptProcessor().encrypt(messageContext, securityContext, false);


            } else if (serviceContext.isSign()) {

                new DocumentSignProcessor().sign(messageContext);


            } else if (securityContext.isPropertyLevelEncryption()) {

                new DocumentEncryptProcessor().encrypt(messageContext, securityContext, false);

            }

        }
    }

}