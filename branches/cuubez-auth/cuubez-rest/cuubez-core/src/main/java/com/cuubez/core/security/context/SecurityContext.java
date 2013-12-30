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

package com.cuubez.core.security.context;


import com.cuubez.core.annotation.context.FieldAnnotationMetaData;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class SecurityContext {

    private Map<String,FieldAnnotationMetaData> tagsToEncrypt = null;
    private Key symmetricKey;
    private Key encSymmetricKey;

    public FieldAnnotationMetaData findTagsToEncrypt(String responseObject) {
        return tagsToEncrypt.get(responseObject);
    }

    public void addTagsToEncrypt(String responseObject, FieldAnnotationMetaData tagsToEnc) {

        if (tagsToEncrypt == null) {
            this.tagsToEncrypt = new HashMap<String, FieldAnnotationMetaData>();
            this.tagsToEncrypt.put(responseObject, tagsToEnc);
        }

        this.tagsToEncrypt.put(responseObject, tagsToEnc);
    }

    public Key getEncSymmetricKey() {
        return encSymmetricKey;
    }

    public void setEncSymmetricKey(Key encSymmetricKey) {
        this.encSymmetricKey = encSymmetricKey;
    }

    public Key getSymmetricKey() {
        return symmetricKey;
    }

    public void setSymmetricKey(Key symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public Map<String, FieldAnnotationMetaData> getTagsToEncrypt() {
        return tagsToEncrypt;
    }

    public void setTagsToEncrypt(Map<String, FieldAnnotationMetaData> tagsToEncrypt) {
        this.tagsToEncrypt = tagsToEncrypt;
    }

    public boolean isPropertyLevelEncryption() {

        if(tagsToEncrypt != null && !tagsToEncrypt.isEmpty()) {
            return true;
        }

        return false;
    }
}
