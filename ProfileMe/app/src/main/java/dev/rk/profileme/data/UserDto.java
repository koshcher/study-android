package dev.rk.profileme.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dev.rk.profileme.models.User;

@Dao
public interface UserDto {
    @Insert
    void insert(User book);

    @Query("select * from user")
    List<User> getAll();

}
