package com.example.mpaiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mpaiproject.models.User;
import com.example.mpaiproject.models.interfaces.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etName;
    private EditText etAdress;
    private Button btnDone;
    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initData();

        etAdress.setText(LoginActivity.user.getAddress());
        etName.setText(LoginActivity.user.getName());
        etEmail.setText(LoginActivity.user.getUsername());


        btnDone.setOnClickListener(view -> {
            if (validate()) {
                getUserInfo();
            }
        });
    }

    public void initData() {
        etEmail = findViewById(R.id.editTextEmailAddressSettings);
        etName = findViewById(R.id.editTextNameSettings);
        etAdress = findViewById(R.id.editTextAdressSettings);
        btnDone = findViewById(R.id.buttonUpdateSettings);

        //populate with data from db user
    }

    private boolean validate() {
        boolean isValid;
        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Please insert your email");
        } else {
            etEmail.setError(null);
        }
        if (etName.getText().toString().trim().isEmpty()) {
            etName.setError("Please insert yout name");
        } else {
            etName.setError(null);
        }
        if (etAdress.getText().toString().trim().isEmpty()) {
            etAdress.setError("Please insert your password");
            isValid = false;
        } else {
            etAdress.setError(null);
            isValid = true;
        }
        return isValid;
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public void getUserInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitClient = retrofit.create(RetrofitClient.class);
        User user = new User();
        user.setUsername(etEmail.getText().toString());
        user.setAddress(etAdress.getText().toString());
        user.setName(etName.getText().toString());
        Call<User> call = retrofitClient.updateUserInfo(LoginActivity.user.getUsername(), user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    LoginActivity.user.setName(response.body().getName());
                    LoginActivity.user.setAddress(response.body().getAddress());
                    LoginActivity.user.setUsername(response.body().getUsername());
                    finish();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}