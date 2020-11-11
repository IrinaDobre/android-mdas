package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etName;
    private EditText etAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initData();
    }

    public void initData() {
        etEmail = findViewById(R.id.editTextEmailAddressRegister);
        etPassword = findViewById(R.id.editTextPasswordRegister);
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        etName = findViewById(R.id.editTextName);
        etAdress = findViewById(R.id.editTextAdress);
    }
}