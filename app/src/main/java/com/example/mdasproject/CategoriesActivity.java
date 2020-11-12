package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView historyCard, artCard, scienceCard, scifiCard, businessCard,
            biographyCard, travelCard, medicalCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        historyCard = findViewById(R.id.historyCard);
        artCard = findViewById(R.id.artCard);
        scienceCard = findViewById(R.id.scienceCard);
        scifiCard = findViewById(R.id.scifiCard);
        businessCard = findViewById(R.id.businessCard);
        biographyCard = findViewById(R.id.bioCard);
        travelCard = findViewById(R.id.travelCard);
        medicalCard = findViewById(R.id.medicalCard);

        historyCard.setOnClickListener(this);
        artCard.setOnClickListener(this);
        scienceCard.setOnClickListener(this);
        scifiCard.setOnClickListener(this);
        businessCard.setOnClickListener(this);
        biographyCard.setOnClickListener(this);
        travelCard.setOnClickListener(this);
        medicalCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.historyCard:
                Toast.makeText(getApplicationContext(), "History", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "history");
                startActivity(intent);
                break;
            case R.id.artCard:
                Toast.makeText(getApplicationContext(), "Art", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "art");
                startActivity(intent);
                break;
            case R.id.scienceCard:
                Toast.makeText(getApplicationContext(), "Science", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "science");
                startActivity(intent);
                break;
            case R.id.businessCard:
                Toast.makeText(getApplicationContext(), "Business", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "business");
                startActivity(intent);
                break;
            case R.id.bioCard:
                Toast.makeText(getApplicationContext(), "Biography", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "biography");
                startActivity(intent);
                break;
            case R.id.medicalCard:
                Toast.makeText(getApplicationContext(), "Medical", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "medical");
                startActivity(intent);
                break;
            case R.id.travelCard:
                Toast.makeText(getApplicationContext(), "Travel", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "travel");
                startActivity(intent);
                break;
            case R.id.scifiCard:
                Toast.makeText(getApplicationContext(), "Sci-Fi", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "scifi");
                startActivity(intent);
                break;
        }
    }



}