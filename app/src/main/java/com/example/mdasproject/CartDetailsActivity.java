package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdasproject.classes.ShoppingCartItem;
import com.example.mdasproject.classes.User;

public class CartDetailsActivity extends AppCompatActivity {

    private TextView tvInvoiceName;
    private TextView tvInvoiceAdress;
    private TextView tvTotalPriceToPay;
    private Button btnInsertDetailsCard;
    private Button btnProcessPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
        initData();

        btnProcessPayment.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(),"The payment was processed!", Toast.LENGTH_LONG).show();
        });

        btnInsertDetailsCard.setOnClickListener(v -> {
            Intent intent = new Intent(this, CardDetailsActivity.class);
            startActivity(intent);
        });
    }

    public void initData() {
        tvInvoiceName = findViewById(R.id.invoiceName);
        tvInvoiceAdress = findViewById(R.id.invoiceAdress);
        tvTotalPriceToPay = findViewById(R.id.totalPriceToPay);
        btnInsertDetailsCard = findViewById(R.id.buttonCardDetails);
        btnProcessPayment = findViewById(R.id.buttonProcessPayment);

        tvInvoiceName.setText(LoginActivity.user.getName());
        tvInvoiceAdress.setText(LoginActivity.user.getAddress());
        double totalPrice = 0.0;
        for (ShoppingCartItem item : User.shoppingList) {
            totalPrice += item.getTotalPrice();
        }
        tvTotalPriceToPay.setText(Double.toString(totalPrice));
    }
}