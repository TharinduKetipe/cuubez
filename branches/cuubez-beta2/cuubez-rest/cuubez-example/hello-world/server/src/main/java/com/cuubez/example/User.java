package com.cuubez.example;


public class User {

    private String id;
    private String name;
    private Double age;

    public User(String id, Double age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
