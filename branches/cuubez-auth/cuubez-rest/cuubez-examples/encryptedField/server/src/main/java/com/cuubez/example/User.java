package com.cuubez.example;


import com.cuubez.core.security.annotation.EncryptedField;

public class User {

    private String name;
    private int age;
    @EncryptedField(name = "password")
    private String password;
    private String address;

    public User(String name, int age, String password, String address) {
        this.address = address;
        this.age = age;
        this.name = name;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
