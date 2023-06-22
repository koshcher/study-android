package rk.retrofiter.retrofit.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rk.retrofiter.retrofit.models.Currency;

public interface PrivatApiService {
    @GET("/p24api/pubinfo?exchange&coursid=5")
    Call<List<Currency>> getCurrencies();
}
