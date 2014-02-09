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
package com.cuubez.core.annotation.context;

import java.util.ArrayList;
import java.util.List;

public class MethodAnnotationMetaData {

    private String methodName = null;
    private Class<?> returnType = null;
    private List<MethodParameter> methodParameters = null;


    public MethodAnnotationMetaData(String methodName, Class<?> returnType) {
        super();
        this.methodName = methodName;
        this.returnType = returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public List<MethodParameter> getMethodParameters() {
        return methodParameters;
    }

    public MethodAnnotationMetaData addMethodParameters(String name, Class<?> parameterType) {

        if (this.methodParameters == null) {
            this.methodParameters = new ArrayList<MethodParameter>();
        }

        MethodParameter methodParam = new MethodParameter(name, parameterType);
        this.methodParameters.add(methodParam);
        return this;
    }


}
