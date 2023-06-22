package dev.rk.roomdbinator.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "user_name")
    public String name;
    @ColumnInfo(name = "user_surname")
    public String surname;

    @Embedded
    public Address address;

    public User( String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
