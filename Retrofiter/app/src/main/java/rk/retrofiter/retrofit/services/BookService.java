package rk.retrofiter.retrofit.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rk.retrofiter.retrofit.models.Book;

public interface BookService {
    @GET("book/{id}")
    Call<Book> getById(@Path("id") int id);

    @GET("book/{id}")
    Call<Book> getById(@Path("id") int id, @Query("name") String name);

    @POST("book")
    Call<Book> createBook(@Body Book book);

    @Headers({"Cache-Control: max-age=1000000"})
    @GET("book")
    Call<Book> getById(@Header("Authorization") String token, @Query("name") String name);
}
