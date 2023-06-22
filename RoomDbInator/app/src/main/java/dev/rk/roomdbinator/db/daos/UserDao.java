package dev.rk.roomdbinator.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import dev.rk.roomdbinator.db.loaders.UserWithBook;
import dev.rk.roomdbinator.db.models.User;

@Dao
public interface UserDao {

    @Insert
    public void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("select * from user")
    public List<User> getAll();

    @Query("select * from user where id=:id")
    User getById(int id);

    @Transaction
    @Query("select * from user join book on user_id = id where id=:id")
    public List<UserWithBook> userWithBooks(int id);
}
