package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etName;
    private EditText etAdress;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initData();

        btnDone.setOnClickListener(view -> {
            if (validate()) {
                updateUser();
            }
        });
    }

    public void initData() {
        etEmail = findViewById(R.id.editTextEmailAddressSettings);
        etPassword = findViewById(R.id.editTextPasswordSettings);
        etConfirmPassword = findViewById(R.id.editTextConfirmPasswordSettings);
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

    private void updateUser() {
        User user = new User();
        user.setUsername(etEmail.getText().toString());
        user.setName(etName.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.setAddress(etAdress.getText().toString());

    }
}