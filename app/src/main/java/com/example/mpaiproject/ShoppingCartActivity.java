package com.example.mpaiproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpaiproject.adapters.ShoppingCartAdapter;
import com.example.mpaiproject.models.ShoppingCartItem;
import com.example.mpaiproject.models.interfaces.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mpaiproject.models.User.shoppingList;
public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShoppingCartAdapter shoppingCartAdapter;
    private FloatingActionButton buttonPayment;
    private RetrofitClient retrofitClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        buttonPayment = findViewById(R.id.buttonPayment);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingCartAdapter = new ShoppingCartAdapter(this, shoppingList);
        recyclerView.setAdapter(shoppingCartAdapter);

        buttonPayment.setOnClickListener(v -> {
            choosePaymentMethod();
        });

    }

    private void choosePaymentMethod() {
        PaymentDialog paymentDialog = PaymentDialog.newInstance();
        paymentDialog.show(getSupportFragmentManager(),"paymanetDialog");
    }

    @Override
    public void onBackPressed() {
        updateShoppingItemList();
        super.onBackPressed();
    }


    public void updateShoppingItemList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitClient = retrofit.create(RetrofitClient.class);
        Call<List<ShoppingCartItem>> call = retrofitClient.updateShoppingItemList(LoginActivity.user.getUsername(), shoppingList);
        call.enqueue(new Callback<List<ShoppingCartItem>>() {
            @Override
            public void onResponse(Call<List<ShoppingCartItem>> call, Response<List<ShoppingCartItem>> response) {
                if (response.code() == 200) {
                    Log.i("Update shopping item list", "Success");
                }
            }

            @Override
            public void onFailure(Call<List<ShoppingCartItem>> call, Throwable t) {
                Toast.makeText(ShoppingCartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}