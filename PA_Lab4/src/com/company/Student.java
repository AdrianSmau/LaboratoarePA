package com.company;

public class Student {
    private String name;
    private double grade;

    //constructor

    public Student(String name) {
        this.name = name;
    }

    //grade methods

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    //name methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student " + this.name;
    }
}
