package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookDetailsActivity extends AppCompatActivity {
    FloatingActionButton favFAB;
    FloatingActionButton cartFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        favFAB = findViewById(R.id.favFAB);
        cartFAB = findViewById(R.id.cartFAB);

        getSupportActionBar().setTitle("");

        Bundle extras = getIntent().getExtras();
        String title ="", authors ="", description="", publishDate="", price ="", thumbnail ="";

        if(extras != null){
            title = extras.getString("bookTitle");
            authors = extras.getString("bookAuthor");
            description = extras.getString("bookDesc");
            publishDate = extras.getString("bookPublishDate");
            thumbnail = extras.getString("bookThumbnail");
            price = extras.getString("bookPrice");
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tvAuthors = findViewById(R.id.author);
        TextView tvDesc = findViewById(R.id.description);
        TextView tvPublishDate = findViewById(R.id.publishDate);
        TextView tvPrice = findViewById(R.id.price);
        ImageView ivThumbnail = findViewById(R.id.thumbnail);

        tvAuthors.setText(authors);
        tvDesc.setText(description);
        tvPublishDate.setText(publishDate);
        tvPrice.setText(price);

        collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.load).error(R.drawable.load);

        Glide.with(this).load(thumbnail).apply(requestOptions).into(ivThumbnail);
    }

}