package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mdasproject.adapters.RecyclerViewAdapter;
import com.example.mdasproject.adapters.ShoppingCartAdapter;
import com.example.mdasproject.classes.Book;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShoppingCartAdapter shoppingCartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingCartAdapter = new ShoppingCartAdapter(this, User.shoppingList);
        recyclerView.setAdapter(shoppingCartAdapter);

    }
}