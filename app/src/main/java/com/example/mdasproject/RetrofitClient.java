package com.example.mdasproject;

import com.example.mdasproject.classes.ShoppingCartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClient {

    @Headers({"Content-type: application/json"})
    @POST("/user/create")
    Call<User> createUser(@Body User user);

    @Headers({"Content-type: application/json"})
    @GET("/user/login")
    Call<User> loginUser(@Query("username") String username, @Query("password") String password);

    @Headers({"Content-type: application/json"})
    @POST("/user/{userEmail}/shoppingItem/add")
    Call<ShoppingCartItem> addShoppingCartItem(@Path("userEmail") String userEmail, @Body ShoppingCartItem shoppingCartItem);

    @Headers({"Content-type: application/json"})
    @GET("/user/{userEmail}/shoppingItem")
    Call<List<ShoppingCartItem>> getShoppingItems(@Path("userEmail") String userEmail);

    @Headers({"Content-type: application/json"})
    @PUT("/user/{userEmail}/shoppingItem/updateAll")
    Call<List<ShoppingCartItem>> updateShoppingItemList(@Path("userEmail") String userEmail, @Body List<ShoppingCartItem> shoppingCartItem);

}
