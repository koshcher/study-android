package dev.rk.employeesmanager.models;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private String image;
    private int age;

    public Employee(String name, String image, int age) {
        this.name = name;
        this.image = image;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }
}
