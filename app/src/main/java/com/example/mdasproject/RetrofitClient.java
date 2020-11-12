package com.example.mdasproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitClient {

    @Headers({"Content-type: application/json"})
    @POST("/user/create")
    Call<User> createUser(@Body User user);

    @Headers({"Content-type: application/json"})
    @GET("/user/login")
    Call<User> loginUser(@Query("username") String username, @Query("password") String password);
}
