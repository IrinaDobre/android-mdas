package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mdasproject.adapters.RecyclerViewAdapter;
import com.example.mdasproject.classes.Book;

import java.util.ArrayList;
import java.util.List;

public class FavoritesListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);

        recyclerView = findViewById(R.id.recyclerViewFavoritesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksAdapter = new RecyclerViewAdapter(this, User.favListBook);
        recyclerView.setAdapter(booksAdapter);
    }
}