package com.cuubez.example;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users/{userId}")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorld {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public User userGet(@HeaderParam(value="name")String name, @PathParam(value = "userId")String id, @QueryParam(value = "age")Double age) {

        User user = new User(id, age, name);
        return user;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public void userPost(User user) {
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
