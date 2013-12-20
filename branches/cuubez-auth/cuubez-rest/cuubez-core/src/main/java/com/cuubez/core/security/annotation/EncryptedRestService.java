package com.cuubez.core.security.annotation;

import com.cuubez.core.annotation.HttpMethod;
import com.cuubez.core.context.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptedRestService {

    String name();

    String path();

    HttpMethod httpMethod();

    MediaType mediaType();

    boolean isSecure() default false;

    String[] userIds() default "[undefined]";

    String[] roleIds() default "[undefined]";
}
