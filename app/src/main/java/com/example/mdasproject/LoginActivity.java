package com.example.mdasproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mdasproject.RegisterActivity.validateEmail;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnCreateAccount;
    private RetrofitClient retrofitClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();


        btnLogin.setOnClickListener(v -> {
//            if (validate()) {
//                if (validateEmail(etEmail.getText().toString())) {
//                    etEmail.setError(null);
//                    Retrofit retrofit = new Retrofit.Builder()
//                            .baseUrl("http://10.0.2.2:8081/")
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .build();
//                    retrofitClient = retrofit.create(RetrofitClient.class);
//                    authenticateUser();
//                } else {
//                    etEmail.setError("Please enter a valid email format");
//                }
//            }

            Intent intent = new Intent(LoginActivity.this, CategoriesActivity.class);
            startActivity(intent);
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
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
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