package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mdasproject.adapters.RecyclerViewAdapter;
import com.example.mdasproject.adapters.ShoppingCartAdapter;
import com.example.mdasproject.classes.Book;
import com.example.mdasproject.classes.ShoppingCartItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShoppingCartAdapter shoppingCartAdapter;
    private FloatingActionButton buttonPayment;


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
}