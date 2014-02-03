package com.cuubez.example;


public class Student {

    private String studentId;
    private String name;
    private String telNo;
    private int grade;

    public Student( String studentId, String name, int grade, String telNo) {
        this.grade = grade;
        this.name = name;
        this.studentId = studentId;
        this.telNo = telNo;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
