package com.example.mdasproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdasproject.adapters.RecyclerViewAdapter;
import com.example.mdasproject.models.Book;
import com.example.mdasproject.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FavoritesListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter booksAdapter;
    private FloatingActionButton btnShareList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);

        getSupportActionBar().setTitle("Favorites");

        btnShareList = findViewById(R.id.buttonShare);
        recyclerView = findViewById(R.id.recyclerViewFavoritesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksAdapter = new RecyclerViewAdapter(this, User.favListBook, 0);
        recyclerView.setAdapter(booksAdapter);

        btnShareList.setOnClickListener(v -> {
            String destinatar = null;

            String subject = "My Book WishList";
            List<String> content = new ArrayList<>();

            for (Book favBook : User.favListBook) {
                content.add(favBook.getTitle() + " - " + favBook.getAuthors());
            }

            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("My WishList: \n\n");
            for(String s : content ){
                messageBuilder.append(s + "\n");
            }

            String message = messageBuilder.toString();

            sendEmail(destinatar,subject,message);

        });
    }

    private void sendEmail(String destinatar, String subiect, String mesaj) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{destinatar});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subiect);
        emailIntent.putExtra(Intent.EXTRA_TEXT,mesaj);

        try{
            startActivity(Intent.createChooser(emailIntent,"Alege un Email Client"));

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}