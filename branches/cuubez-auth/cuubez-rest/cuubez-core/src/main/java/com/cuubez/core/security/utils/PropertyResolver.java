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

package com.cuubez.core.security.utils;


import java.util.HashMap;
import java.util.Map;

public class PropertyResolver {

    static Map<String, String> properties = new HashMap<String, String>();

    private static void init() {

        properties.put("keystoreType", "JKS");
        properties.put("keystoreFile", "/home/pa40261/software/key/keystore.jks");
        properties.put("keystoreFileForSign", "/home/pa40261/software/keykeystore.jks");
        properties.put("keystorePass", "password");
        properties.put("privateKeyAlias", "test");
        properties.put("privateKeyPass", "xmlsecurity");
        properties.put("certificateAlias", "test");

    }


    public static String getProperty(String key) {
        init();
        return properties.get(key);
    }
}
