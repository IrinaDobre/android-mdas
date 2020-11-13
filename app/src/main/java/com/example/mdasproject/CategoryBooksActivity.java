package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mdasproject.adapters.RecyclerViewAdapter;
import com.example.mdasproject.classes.Book;
import com.example.mdasproject.classes.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryBooksActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    private List<Book> categoryBooks = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter booksAdapter;
    private ProgressBar progressBar;
    String pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_books);

        progressBar = findViewById(R.id.loadingIndicator);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(booksAdapter);

        pressed = getIntent().getExtras().getString("pressedCategory");
        Log.d("PRESSED RECEIVED", pressed);

        getSupportActionBar().setTitle(pressed.toUpperCase());


        new FetchBooks().execute(pressed);

    }

    public void favoriteIconClicked(View view) {
        Toast.makeText(getApplicationContext(), "Fav icon clicked!", Toast.LENGTH_SHORT).show();
    }


    public class FetchBooks extends AsyncTask<String, Void, String> {
//    List<Book> categoryBooks = new ArrayList<>();

        public FetchBooks() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            return NetworkUtils.getBookInfo(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            categoryBooks.clear();

            String title = "";
            String author = "";
            String publishedDate = "Not available";
            String description = "No description";
            String thumbnail = "";
            int pageCount = 1000;
            String categories = "No categories available ";
            String buy = "";
            String price = "Currently not available";
            String previewLink = "";
            String url = "";

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    JSONObject volumeInfo = item.getJSONObject("volumeInfo");


                    try {
                        title = volumeInfo.getString("title");

                        if(volumeInfo.has("authors")){
                            Log.d("******HAS AUTHORS", String.valueOf(volumeInfo.has("authors")));
                            JSONArray authors = volumeInfo.getJSONArray("authors");

                            if (authors.length() == 1) {
                                author = authors.getString(0);
                            } else {
                                author = authors.getString(0) + " | " + authors.getString(1);
                            }
                        }

                        if(volumeInfo.has("publishedDate")){
                            publishedDate = volumeInfo.getString("publishedDate");
                        }

                        if(volumeInfo.has("pageCount")){
                            pageCount = volumeInfo.getInt("pageCount");
                        }

                        if(item.has("saleInfo")) {
                            JSONObject saleInfo = item.getJSONObject("saleInfo");

                            if(saleInfo.has("listPrice")){
                                Log.d("******HAS LISTPRICE", String.valueOf(volumeInfo.has("listPrice")));
                                JSONObject listPrice = saleInfo.getJSONObject("listPrice");
                                price = listPrice.getString("amount") + " " +listPrice.getString("currencyCode");
                            }

                            if(saleInfo.has("buyLink")){
                                buy = saleInfo.getString("buyLink");
                            }
                        }

                        if(volumeInfo.has("description")){
                            description = volumeInfo.getString("description");
                        }

                        if(volumeInfo.has("categories")){
                            categories = volumeInfo.getJSONArray("categories").getString(0);
                        }

                    } catch (Exception e) {
                        Log.e("ERR", e.getMessage());
                    }

                    if(volumeInfo.has("imageLinks")){
                        Log.d("***********HAS THUMBNAIL", String.valueOf(volumeInfo.has("imageLinks") && volumeInfo.has("thumbnail")));
                        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");

                        if(imageLinks.has("thumbnail")){
                            thumbnail = imageLinks.getString("thumbnail");
                        }

                    }

                    if(volumeInfo.has("previewLink")){
                        previewLink = volumeInfo.getString("previewLink");
                    }

                    if(volumeInfo.has("url")){
                        url = volumeInfo.getString("infoLink");
                    }

                    categoryBooks.add(new Book(title, author, publishedDate, description,
                            categories, thumbnail, buy, previewLink, price, pageCount, url));


                    progressBar.setVisibility(View.GONE);
                    booksAdapter = new RecyclerViewAdapter(CategoryBooksActivity.this, categoryBooks);
                    recyclerView.setAdapter(booksAdapter);


                }
            } catch (JSONException e) {
                Log.e("ERR_JSON", e.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                booksAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}