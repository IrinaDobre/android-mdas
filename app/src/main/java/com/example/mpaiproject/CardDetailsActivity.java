package com.example.mpaiproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CardDetailsActivity extends AppCompatActivity {

    private Button btnSaveCardDetails;
    private EditText etCardNumber;
    private EditText etExpirationDate;
    private EditText etCVV;
    private EditText etCardHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        initData();

        btnSaveCardDetails.setOnClickListener(v -> {
            if (validate()) {
                finish();
            } else {
                Toast.makeText(getApplicationContext(),"Please complete with correct data!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initData() {
        btnSaveCardDetails = findViewById(R.id.buttonSaveCardDetails);
        etCardNumber = findViewById(R.id.editTextCardNumber);
        etExpirationDate = findViewById(R.id.editTextExpirationDate);
        etCVV = findViewById(R.id.editTextCVV);
        etCardHolder = findViewById(R.id.editTextCardHolder);
    }

    private boolean validate() {
        boolean isValid;
        if(etCardNumber.getText().toString().trim().isEmpty()) {
            etCardNumber.setError("Please insert your card number");
            isValid = false;
        } else if (etCardNumber.getText().toString().length() != 16) {
            etCardNumber.setError("Invalid card number");
            isValid = false;
        }else {
            etCardNumber.setError(null);
            isValid = true;
        }
        if(etExpirationDate.getText().toString().trim().isEmpty()) {
            etExpirationDate.setError("Please insert expiration date");
            isValid = false;
        } else if (etExpirationDate.getText().toString().length() != 5) {
            etExpirationDate.setError("Invalid expiration date");
            isValid = false;
        }else {
            etExpirationDate.setError(null);
            isValid = true;
        }
        if(etCVV.getText().toString().trim().isEmpty()) {
            etCVV.setError("Please insert the CVV number");
            isValid = false;
        } else if (etCVV.getText().toString().length() != 3) {
            etCVV.setError("Invalid CVV number");
            isValid = false;
        }else {
            etCVV.setError(null);
            isValid = true;
        }
        if(etCardHolder.getText().toString().trim().isEmpty()) {
            etCardHolder.setError("Please insert the card holder name");
            isValid = false;
        } else {
            etCardHolder.setError(null);
            isValid = true;
        }
        return isValid;
    }
}