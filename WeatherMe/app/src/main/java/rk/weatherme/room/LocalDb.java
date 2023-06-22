package rk.weatherme.room;

import androidx.room.RoomDatabase;

import rk.weatherme.room.dtos.DbWeatherDao;
import rk.weatherme.room.models.DbWeather;

@androidx.room.Database(version = 1, entities = {DbWeather.class})
public abstract class LocalDb extends RoomDatabase {
    public abstract DbWeatherDao weather();
}
