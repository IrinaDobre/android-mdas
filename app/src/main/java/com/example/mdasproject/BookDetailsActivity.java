package com.example.mdasproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mdasproject.classes.Book;
import com.example.mdasproject.classes.ShoppingCartItem;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookDetailsActivity extends AppCompatActivity {
    FloatingActionButton favFAB;
    FloatingActionButton cartFAB;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView tvAuthors;
    TextView tvDesc;
    TextView tvPublishDate;
    TextView tvPrice;
    ImageView ivThumbnail;
    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        favFAB = findViewById(R.id.favFAB);
        cartFAB = findViewById(R.id.cartFAB);

        getSupportActionBar().setTitle("");

        Bundle extras = getIntent().getExtras();
        String title = "", authors = "", description = "", publishDate = "", price = "", thumbnail = "";
        int fromFavList = 0;

        if (extras != null) {
            title = extras.getString("bookTitle");
            authors = extras.getString("bookAuthor");
            description = extras.getString("bookDesc");
            publishDate = extras.getString("bookPublishDate");
            thumbnail = extras.getString("bookThumbnail");
            price = extras.getString("bookPrice");
            fromFavList = extras.getInt("favScreen");
        }

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        tvAuthors = findViewById(R.id.author);
        tvDesc = findViewById(R.id.description);
        tvPublishDate = findViewById(R.id.publishDate);
        tvPrice = findViewById(R.id.price);
        ivThumbnail = findViewById(R.id.thumbnail);

        tvAuthors.setText(authors);
        tvDesc.setText(description);
        tvPublishDate.setText(publishDate);
        tvPrice.setText(price);

        collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.load).error(R.drawable.load);

        Glide.with(this).load(thumbnail).apply(requestOptions).into(ivThumbnail);

        String finalThumbnail = thumbnail;
        if (fromFavList == 1) {
            favFAB.setVisibility(View.GONE);
        } else {
            favFAB.setVisibility(View.VISIBLE);

            favFAB.setOnClickListener(v -> {
                Book favBook = new Book();
                favBook.setAuthors(tvAuthors.getText().toString());
                favBook.setTitle(collapsingToolbarLayout.getTitle().toString());
                favBook.setDescription(tvDesc.getText().toString());
                favBook.setPublishedDate(tvPublishDate.getText().toString());
                favBook.setPrice(tvPrice.getText().toString());
                favBook.setThumbnail(finalThumbnail);
                User.favListBook.add(favBook);
                Toast.makeText(getApplicationContext(), "The book was added to your 'Favorites List'", Toast.LENGTH_SHORT).show();
            });
        }


        cartFAB.setOnClickListener(v -> {
            if (tvPrice.getText().toString().equals("Currently not available")) {
                cartFAB.setVisibility(View.GONE);
            } else {
                cartFAB.setVisibility(View.VISIBLE);
                cartFAB.setOnClickListener(v1 -> {
                    if (User.shoppingList.stream().anyMatch(item -> item.getTitle().equals(collapsingToolbarLayout.getTitle().toString()))) {
                        Toast.makeText(getApplicationContext(), "The book is already in your shopping cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.0.2.2:8081/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        retrofitClient = retrofit.create(RetrofitClient.class);

                        ShoppingCartItem cartItem = new ShoppingCartItem();
                        cartItem.setTitle(collapsingToolbarLayout.getTitle().toString());
                        cartItem.setAuthors(tvAuthors.getText().toString());
                        cartItem.setQuantity(1);
                        String[] priceArray = tvPrice.getText().toString().split("\\s+");
                        cartItem.setTotalPrice(Double.parseDouble(priceArray[0])); //Double.valueOf(tvPrice.getText().toString())
                        cartItem.setInitialPrice(Double.parseDouble(priceArray[0]));
                        cartItem.setImageBook(finalThumbnail);
                        addShoopingItem(cartItem);
                        User.shoppingList.add(cartItem);
                        Toast.makeText(getApplicationContext(), "The book was added to your shopping cart", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }


    public void addShoopingItem(ShoppingCartItem shoppingCartItem){
        Call<ShoppingCartItem> call = retrofitClient.addShoppingCartItem(LoginActivity.user.getUsername(), shoppingCartItem);
        call.enqueue(new Callback<ShoppingCartItem>() {
            @Override
            public void onResponse(Call<ShoppingCartItem> call, Response<ShoppingCartItem> response) {
                if (response.code() == 200) {
                    Log.i("Shopping cart call to add item", "Succes");
                } else {
                    Log.i("Shopping cart call to add item", "Failed");
                }
            }

            @Override
            public void onFailure(Call<ShoppingCartItem> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }


}