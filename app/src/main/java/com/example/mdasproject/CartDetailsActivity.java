package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdasproject.models.Card;
import com.example.mdasproject.models.ShoppingCartItem;
import com.example.mdasproject.models.User;
import com.example.mdasproject.proxy.CardProxy;
import com.example.mdasproject.proxy.ICard;

public class CartDetailsActivity extends AppCompatActivity {

    private TextView tvInvoiceName;
    private TextView tvInvoiceAdress;
    private TextView tvTotalPriceToPay;
    private Button btnInsertDetailsCard;
    private Button btnProcessPayment;
    private EditText etCardType;
    private RadioGroup radioGroup;
    private RadioButton radioButtonDeny;
    private TextView chosenOptions;
    public static String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
        initData();

        btnProcessPayment.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(),"The payment was processed!", Toast.LENGTH_LONG).show();
            result = "";
        });

        btnInsertDetailsCard.setOnClickListener(v -> {
            ICard cardProxy = new CardProxy(new Card());
            cardProxy.selectCard(etCardType.getText().toString());

            if (CardProxy.flagCardAccepted) {
                Intent intent = new Intent(this, CardDetailsActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "The type of selected card is not supported by this application!", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void initData() {
        tvInvoiceName = findViewById(R.id.invoiceName);
        tvInvoiceAdress = findViewById(R.id.invoiceAdress);
        tvTotalPriceToPay = findViewById(R.id.totalPriceToPay);
        btnInsertDetailsCard = findViewById(R.id.buttonCardDetails);
        btnProcessPayment = findViewById(R.id.buttonProcessPayment);
        etCardType = findViewById(R.id.editTextCardType);
        radioGroup = findViewById(R.id.radioGroupWrapper);
        chosenOptions = findViewById(R.id.chosenOptions);
        radioButtonDeny = findViewById(R.id.radioNo);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("IDRB", String.valueOf(i));
                RadioButton rb = (RadioButton) radioGroup.findViewById(i);
                if(rb.getText().toString().equals("Yes")){
                    Intent intent = new Intent(CartDetailsActivity.this, CustomPackageActivity.class);
                    startActivity(intent);
                }
                else chosenOptions.setText("");
            }
        });

        tvInvoiceName.setText(LoginActivity.user.getName());
        tvInvoiceAdress.setText(LoginActivity.user.getAddress());
        double totalPrice = 0.0;
        for (ShoppingCartItem item : User.shoppingList) {
            totalPrice += item.getTotalPrice();
        }
        tvTotalPriceToPay.setText(Double.toString(totalPrice));
    }

    @Override
    protected void onPause() {
        result="";
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        if(result.equals("")){
            radioButtonDeny.setChecked(true);
        }
        else chosenOptions.setText(result);

        super.onResume();
    }


}