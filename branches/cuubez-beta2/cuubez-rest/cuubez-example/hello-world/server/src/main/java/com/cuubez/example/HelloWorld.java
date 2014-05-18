package com.cuubez.example;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users/{userId}")
public class HelloWorld {

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("detail")
    public User userGet(@HeaderParam(value="name")String name, @PathParam(value = "userId")String id, @QueryParam(value = "age")int age) {

        User user = new User(id, age, name);
        return user;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("detail")
    public void userPost(User user) {
        System.out.println("POST");
        System.out.println("Name =" + user.getName());
        System.out.println("Age =" + user.getAge());

    }

    @PUT
    @Path("detail")
    public void userPut() {
        System.out.println("PUT");

    }

    @DELETE
    @Path("detail")
    public void userDelete() {
        System.out.println("DELETE");
    }
}
