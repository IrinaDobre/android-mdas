package com.example.mpaiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etName;
    private EditText etAdress;
    private Button btnDone;
    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initData();


        btnDone.setOnClickListener(view -> {
            if (validate()) {
                if (validateEmail(etEmail.getText().toString())) {
                    etEmail.setError(null);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:8081/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    retrofitClient = retrofit.create(RetrofitClient.class);
                    createUser();
                } else {
                    etEmail.setError("Please enter a valid email format");
                }
            }
        });
    }

    public void initData() {
        etEmail = findViewById(R.id.editTextEmailAddressRegister);
        etPassword = findViewById(R.id.editTextPasswordRegister);
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        etName = findViewById(R.id.editTextName);
        etAdress = findViewById(R.id.editTextAdress);
        btnDone = findViewById(R.id.button);
    }

    private void createUser() {
        User user = new User();
        user.setUsername(etEmail.getText().toString());
        user.setName(etName.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.setAddress(etAdress.getText().toString());

        Call<User> call = retrofitClient.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 201) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_LONG).show();
                }
                if (response.code() == 409) {
                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();
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
        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Please insert your email");
        } else {
            etEmail.setError(null);
        }
        if (etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError("Please insert your password");
        } else {
            etPassword.setError(null);
        }
        if(etConfirmPassword.getText().toString().trim().isEmpty()) {
            etConfirmPassword.setError("Please confirm your password");
        } else if (!(etConfirmPassword.getText().toString().equals(etPassword.getText().toString()))) {
            etConfirmPassword.setError("Password doesn't match");
            etConfirmPassword.setText("");
        } else {
            etConfirmPassword.setError(null);
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
}