package dev.rk.roomdbinator.db.context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.rk.roomdbinator.db.daos.BookDao;
import dev.rk.roomdbinator.db.daos.UserDao;
import dev.rk.roomdbinator.db.models.Book;
import dev.rk.roomdbinator.db.models.User;

@Database(version = 1, entities = {User.class, Book.class})
public abstract class InatorContext extends RoomDatabase {

    public abstract UserDao user();
    public abstract BookDao book();
}
