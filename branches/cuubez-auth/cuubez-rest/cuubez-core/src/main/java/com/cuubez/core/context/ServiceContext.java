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
package com.cuubez.core.context;

import com.cuubez.core.annotation.context.ServiceAnnotationMetaData;

public class ServiceContext {

    private String packageName = null;
    private Class<?> serviceClass = null;
    private String serviceName = null;
    private MediaType mediaType = null;
    private boolean sign = false;
    private boolean encrypt = false;
    private boolean isSecure = false;
    private String[] userIds = null;
    private String[] roleIds = null;
    private ServiceAnnotationMetaData serviceAnnotationMetaData = null;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Class<?> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public ServiceAnnotationMetaData getServiceAnnotationMetaData() {
        return serviceAnnotationMetaData;
    }

    public ServiceAnnotationMetaData addServiceAnnotationMetaData(String annotationName, String serviceLocation) {
        return this.serviceAnnotationMetaData = new ServiceAnnotationMetaData(annotationName, serviceLocation);
    }

	public boolean isSecure() {
		return isSecure;
	}

	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}

    public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }
}
