package com.example.mdasproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private CardView historyCard, artCard, scienceCard, scifiCard, businessCard,
            biographyCard, travelCard, medicalCard;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

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


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_menu);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle );
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.historyCard:
//                Toast.makeText(getApplicationContext(), "History", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "history");
                startActivity(intent);
                break;
            case R.id.artCard:
//                Toast.makeText(getApplicationContext(), "Art", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "art");
                startActivity(intent);
                break;
            case R.id.scienceCard:
//                Toast.makeText(getApplicationContext(), "Science", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "science");
                startActivity(intent);
                break;
            case R.id.businessCard:
//                Toast.makeText(getApplicationContext(), "Business", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "business");
                startActivity(intent);
                break;
            case R.id.bioCard:
//                Toast.makeText(getApplicationContext(), "Biography", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "biography");
                startActivity(intent);
                break;
            case R.id.medicalCard:
//                Toast.makeText(getApplicationContext(), "Medical", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "medical");
                startActivity(intent);
                break;
            case R.id.travelCard:
//                Toast.makeText(getApplicationContext(), "Travel", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "travel");
                startActivity(intent);
                break;
            case R.id.scifiCard:
//                Toast.makeText(getApplicationContext(), "Sci-Fi", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), CategoryBooksActivity.class);
                intent.putExtra("pressedCategory", "scifi");
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.nav_fav_list :
                Toast.makeText(getApplicationContext(),"Lista Favorite", Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(this, FavoritesListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_shopping_cart :
                Toast.makeText(getApplicationContext(),"Lista Cumparaturi", Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_settings :
                Toast.makeText(getApplicationContext(),"Setari", Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout :
                Toast.makeText(getApplicationContext(),"Logout", Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }

        return true;
    }


}