package rk.weatherme.room.dtos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import rk.weatherme.room.models.DbWeather;

@Dao
public interface DbWeatherDao {
    @Insert
    void insert(DbWeather weather);

    @Query("select * from dbweather")
    List<DbWeather> getAll();
}
