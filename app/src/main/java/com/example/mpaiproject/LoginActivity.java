package com.example.mpaiproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpaiproject.models.designpatterns.builder.Book;
import com.example.mpaiproject.models.ShoppingCartItem;
import com.example.mpaiproject.models.User;
import com.example.mpaiproject.models.interfaces.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mpaiproject.RegisterActivity.validateEmail;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnCreateAccount;
    private RetrofitClient retrofitClient;
    public static User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();


        btnLogin.setOnClickListener(v -> {
            if (validate()) {
                if (validateEmail(etEmail.getText().toString())) {
                    etEmail.setError(null);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:8081/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    retrofitClient = retrofit.create(RetrofitClient.class);
                    authenticateUser();
                } else {
                    etEmail.setError("Please enter a valid email format");
                }
            }

        });

        btnCreateAccount.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    public void initData() {
        etEmail = findViewById(R.id.editTextTextEmailAddress);
        etPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnCreateAccount = findViewById(R.id.buttonCreateAccount);
    }

    private void authenticateUser() {
        Call<User> call = retrofitClient.loginUser(etEmail.getText().toString(), etPassword.getText().toString());
        String email = etEmail.getText().toString();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    user.setUsername(email);
                    user.setAddress(response.body().getAddress());
                    user.setName(response.body().getName());
                    getUserShoppingItems(email);
                    getUserFavoriteItems(email);
                    Intent intent = new Intent(LoginActivity.this, CategoriesActivity.class);
                    startActivity(intent);
                }
                if (response.code() == 404) {
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void getUserShoppingItems(String userEmail) {
        Call<List<ShoppingCartItem>> call = retrofitClient.getShoppingItems(userEmail);
        call.enqueue(new Callback<List<ShoppingCartItem>>() {
            @Override
            public void onResponse(Call<List<ShoppingCartItem>> call, Response<List<ShoppingCartItem>> response) {
                if (response.code() == 200) {
                    User.shoppingList.clear();
                    if (response.body() != null) {
                        User.shoppingList.addAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ShoppingCartItem>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUserFavoriteItems(String userEmail) {
        Call<List<Book>> call = retrofitClient.getFavoriteItems(userEmail);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.code() == 200) {
                    User.favListBook.clear();
                    if(response.body() != null) {
                        User.favListBook.addAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate() {
        boolean isValid;
        if(etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Please insert your email");
        } else {
            etEmail.setError(null);
        }
        if(etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError("Please insert your password");
            isValid = false;
        } else {
            etPassword.setError(null);
            isValid = true;
        }
        return isValid;
    }
}