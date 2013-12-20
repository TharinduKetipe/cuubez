package com.cuubez.example;


import com.cuubez.core.annotation.HttpMethod;
import com.cuubez.core.annotation.RestService;
import com.cuubez.core.context.MediaType;

public class UserService {

    @RestService(name = "userDetail", path = "/user", mediaType = MediaType.XML, httpMethod = HttpMethod.GET,isSecure = false,roleIds = "",userIds = "")
    public User getUserDetail() {

        User user = new User("test user", 25, "password", "test address");

        return user;

    }

}
