package dev.rk.profileme.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String image;
    public int age;
    public String location;
    public String bio;


    public User(String name, String image, int age, String location, String bio)
    {
        this.name = name;
        this.image = image;
        this.age = age;
        this.location = location;
        this.bio = bio;
    }
}
