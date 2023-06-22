package rk.weatherme.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.room.Room;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rk.weatherme.api.RetrofitClient;
import rk.weatherme.api.models.Weather;
import rk.weatherme.api.services.WeatherApi;
import rk.weatherme.room.LocalDb;
import rk.weatherme.room.models.DbWeather;

public class CollectorService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LocalDb db = Room.databaseBuilder(getApplicationContext(), LocalDb.class, "weather_db")
                .build();

        WeatherApi weatherApi = RetrofitClient.getRetrofit().create(WeatherApi.class);
        weatherApi.getCurrent().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if(!response.isSuccessful()) return;

                Weather weather = response.body();

                DbWeather dbWeather = new DbWeather(
                        weather.getLocation().getName(),
                        weather.getLocation().getLocaltime(),
                        weather.getCurrent().getTempC(),
                        weather.getCurrent().getIsDay(),
                        weather.getCurrent().getWindKph(),
                        weather.getCurrent().getWindDegree(),
                        weather.getCurrent().getWindDir()
                );

                new Thread(() -> db.weather().insert(dbWeather)).start();
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {}
        });

        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }
}
