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
package com.cuubez.example;

import com.cuubez.core.annotation.security.DenyAll;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users/{userId}")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    @DenyAll
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public com.cuubez.example.User userGet(@HeaderParam(value="name")String name, @PathParam(value = "userId")String id, @QueryParam(value = "age")Double age) {

        com.cuubez.example.User user = new com.cuubez.example.User(id, age, name);
        return user;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public void userPost(com.cuubez.example.User user) {
        System.out.println("POST");
        System.out.println("Name =" + user.getName());
        System.out.println("Age =" + user.getAge());

    }

    @PUT
    public void userPut() {
        System.out.println("PUT");

    }

    @DELETE
    public void userDelete() {
        System.out.println("DELETE");
    }
}
