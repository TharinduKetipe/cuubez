package com.cuubez.example;


import com.cuubez.core.annotation.HttpMethod;
import com.cuubez.core.context.MediaType;
import com.cuubez.core.security.annotation.EncryptedRestService;

public class HelloWorld {

   @EncryptedRestService(name = "message", path = "/detail", httpMethod = HttpMethod.GET, mediaType = MediaType.XML)
   public String helloWorld() {
       return "Hello World";
   }

}
