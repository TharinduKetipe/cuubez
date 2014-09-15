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
package com.cuubez.visualizer.resource;


import com.cuubez.visualizer.context.ApiMetaData;
import com.cuubez.visualizer.util.CuubezUtil;

import java.util.ArrayList;
import java.util.List;

public class ApiMetaDataResolver {

    public void resolve() {


        List<RootResource> rootResourceList = ResourceRepository.getInstance().getRootResources();

        List<ApiMetaData> apiMetaDataList = new ArrayList<ApiMetaData>();

        for (RootResource rootResource : rootResourceList) {

            for (SubResource subResource : rootResource.getSubResources()) {

                String path = CuubezUtil.constructApiPath(rootResource.getClassMetaData().getPath(), subResource.getMethodMetaData().getPath());

                ApiMetaData apiMetaData = new ApiMetaData(subResource.getMethodMetaData().getHttpMethod(), path);
                apiMetaData.setName(CuubezUtil.getName(rootResource.getClassMetaData().getName(), subResource.getMethodMetaData().getName()));
                apiMetaData.setDetail(CuubezUtil.getDetail(rootResource.getClassMetaData().getDetail(), subResource.getMethodMetaData().getDetail()));
                apiMetaData.setPathVariableMetaDataList(subResource.getPathVariableMetaDataList());
                apiMetaData.setQueryVariableMetaDataList(subResource.getQueryVariableMetaDataList());
                apiMetaData.setHeaderVariableMetaDataList(subResource.getHeaderVariableMetaDataList());
                if(subResource.getRequestBody() != null) {
                    apiMetaData.setRequestBody(CuubezUtil.generateJsonSchema(subResource.getRequestBody()));
                }
                apiMetaData.setHttpCodeMetaDataList(CuubezUtil.getHttpCodes(rootResource.getClassMetaData().getHttpCodeMetaDataList(), subResource.getMethodMetaData().getHttpCodeMetaDataList()));

                if(subResource.getMethodMetaData().getReturnType() != null) {
                    apiMetaData.setResponseBody(CuubezUtil.generateJsonSchema(subResource.getMethodMetaData().getReturnType()));
                }
                apiMetaDataList.add(apiMetaData);
            }


        }

        ResourceRepository.getInstance().setApiMetaData(apiMetaDataList);

    }
}
