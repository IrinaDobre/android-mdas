package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.view.View;

import com.example.mdasproject.adapters.RecyclerViewAdapter;
import com.example.mdasproject.adapters.ShoppingCartAdapter;
import com.example.mdasproject.classes.Book;
import com.example.mdasproject.classes.ShoppingCartItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShoppingCartAdapter shoppingCartAdapter;
    private FloatingActionButton buttonPayment;
    private RetrofitClient retrofitClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        buttonPayment = findViewById(R.id.buttonPayment);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingCartAdapter = new ShoppingCartAdapter(this, User.shoppingList);
        recyclerView.setAdapter(shoppingCartAdapter);

        buttonPayment.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartDetailsActivity.class);
            startActivity(intent);
        });

    }


    @Override
    public void onBackPressed() {
        updateShoppingItemList();
        super.onBackPressed();
    }


    public void updateShoppingItemList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitClient = retrofit.create(RetrofitClient.class);
        Call<List<ShoppingCartItem>> call = retrofitClient.updateShoppingItemList(LoginActivity.user.getUsername(),User.shoppingList);
        call.enqueue(new Callback<List<ShoppingCartItem>>() {
            @Override
            public void onResponse(Call<List<ShoppingCartItem>> call, Response<List<ShoppingCartItem>> response) {
                if (response.code() == 200) {
                    Log.i("Update shopping item list", "Success");
                }
            }

            @Override
            public void onFailure(Call<List<ShoppingCartItem>> call, Throwable t) {
                Toast.makeText(ShoppingCartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}