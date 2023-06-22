package dev.rk.roomdbinator.db.loaders;

import androidx.room.Embedded;
import androidx.room.Relation;

import dev.rk.roomdbinator.db.models.Book;
import dev.rk.roomdbinator.db.models.User;

public class UserWithBook {
    @Embedded
    public User user;

    @Relation(parentColumn = "id", entityColumn = "user_id")
    public Book book;
}
