package dev.rk.profileme.data;

import androidx.room.RoomDatabase;

import dev.rk.profileme.models.User;


@androidx.room.Database(version = 1, entities = {User.class})
public abstract class Database extends RoomDatabase {

    public abstract UserDto user();
}
