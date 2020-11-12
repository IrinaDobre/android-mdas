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

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        getSupportActionBar().setTitle("");

        Bundle extras = getIntent().getExtras();
        String title ="", authors ="", description="", publishDate="", info ="",
                buy ="", thumbnail ="";


        if(extras != null){
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            description = extras.getString("book_desc");
            publishDate = extras.getString("book_publish_date");
            thumbnail = extras.getString("book_thumbnail");
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tvAuthors = findViewById(R.id.aa_author);
        TextView tvDesc = findViewById(R.id.aa_description);
        TextView tvPublishDate = findViewById(R.id.aa_publish_date);

        ImageView ivThumbnail = findViewById(R.id.aa_thumbnail);

        tvAuthors.setText(authors);
        tvDesc.setText(description);
        tvPublishDate.setText(publishDate);


        collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.load).error(R.drawable.load);

        Glide.with(this).load(thumbnail).apply(requestOptions).into(ivThumbnail);
    }

}