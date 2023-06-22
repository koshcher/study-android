package rk.weatherme.room.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class DbWeather {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String location;
    public String localtime;

    public Double tempC;
    public Double isDay;
    public Double windKph;
    public Double windDegree;
    public String windDir;

    public DbWeather(
            String location,
            String localtime,
            Double tempC,
            Double isDay,
            Double windKph,
            Double windDegree,
            String windDir
    ) {
        this.localtime = localtime;
        this.location = location;
        this.tempC = tempC;
        this.isDay = isDay;
        this.windDegree = windDegree;
        this.windKph = windKph;
        this.windDir = windDir;
    }
}
