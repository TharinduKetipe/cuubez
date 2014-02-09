package com.cuubez.example;


import com.cuubez.core.annotation.HttpMethod;
import com.cuubez.core.context.MediaType;
import com.cuubez.core.security.annotation.SignRestService;

public class StudentService {

    @SignRestService(path = "student", httpMethod = HttpMethod.GET, mediaType = MediaType.XML, isSecure = false, userIds = "", roleIds = "")
    public Student getStudentDetail() {
        Student student = new Student("student-id", "student name", 10, "0776884673" );
        return student;
    }
}
