package dev.rk.courseshmors.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
