package dev.rk.roomdbinator.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import dev.rk.roomdbinator.db.models.Book;
import dev.rk.roomdbinator.db.models.User;

@Dao
public abstract class BookDao {

    @Insert
    public abstract void insert(Book book);

    @Delete
    public abstract void delete(Book book);

    @Update
    public abstract void update(Book book);

    @Query("select * from book")
    public abstract List<Book> getAll();

    @Query("select * from book where id=:id")
    public abstract Book getById(int id);

    @Transaction
    public void replaceBook(Book book, Book newBook) {
        delete(book);
        insert(newBook);
    }
}
