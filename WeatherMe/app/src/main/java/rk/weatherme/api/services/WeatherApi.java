package rk.weatherme.api.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rk.weatherme.api.models.Weather;

public interface WeatherApi {
    @GET("/v1/current.json?q=new_york&lang=en&key=885129a2e39b462f917171741231206")
    Call<Weather> getCurrent();

    @GET("/v1/current.json?lang=en&key=885129a2e39b462f917171741231206")
    Call<Weather> getByTown(@Query("q") String town);
    //Call<Book> getById(@Path("id") int id, @Query("name") String name);
}
