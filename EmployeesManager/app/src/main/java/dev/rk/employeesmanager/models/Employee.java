package dev.rk.employeesmanager.models;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name = "";
    private String image = "";
    private int age = 18;

    public Employee(String name, String image, int age) {
        this.name = name;

        this.image = image.isEmpty() ?
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Windows_10_Default_Profile_Picture.svg/2048px-Windows_10_Default_Profile_Picture.svg.png"
                : image;
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
