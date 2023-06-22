package dev.rk.roomdbinator.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = User.class, parentColumns = "id",
        childColumns = "user_id", onDelete = ForeignKey.CASCADE
))
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_id")
    public int userId;

    public String title;

    public Book(int userId, String title) {
        this.userId = userId;
        this.title = title;
    }

}
