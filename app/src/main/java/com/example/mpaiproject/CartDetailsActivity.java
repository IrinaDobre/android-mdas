package com.example.mpaiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpaiproject.models.designpatterns.adapter.Currency;
import com.example.mpaiproject.models.designpatterns.adapter.CurrencyDollar;
import com.example.mpaiproject.models.designpatterns.adapter.CurrencyDollarAdapter;
import com.example.mpaiproject.models.designpatterns.adapter.CurrencyEuro;
import com.example.mpaiproject.models.designpatterns.adapter.CurrencyRon;
import com.example.mpaiproject.models.Card;
import com.example.mpaiproject.models.ShoppingCartItem;
import com.example.mpaiproject.models.User;
import com.example.mpaiproject.models.designpatterns.proxy.CardProxy;
import com.example.mpaiproject.models.designpatterns.proxy.ICard;

import java.math.BigDecimal;

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
    private TextView tvPriceText;
    private static double FINAL_PRICE;
    public static String result = "";
    private Button btnAddExtraDecorations;
    public static String decorationResult = "";
    private TextView choosenDecorations;
    public static String SELECTED_TYPE_OF_PACKAGE = "";
    public static double extraDecorationPrice;
    public double totalPrice;
    public Currency selectedCurrency = new CurrencyRon();

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

        btnAddExtraDecorations.setOnClickListener(v -> {
            ExtraDecorationsDialog decorationsDialog = ExtraDecorationsDialog.newInstance();
            decorationsDialog.show(getSupportFragmentManager(),"extraDecorationDialog");
        });

        showPayment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_currency, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.currencyEURO) {
            CurrencyEuro currencyEuro = new CurrencyEuro();
            selectedCurrency = currencyEuro;
            double price = Double.parseDouble(String.valueOf(FINAL_PRICE));
            tvPriceText.setText("Total EURO to pay");
            tvTotalPriceToPay.setText(String.valueOf(showTotalPriceToPay(currencyEuro, BigDecimal.valueOf(price))));
        }
        if (item.getItemId() == R.id.currencyUSD) {
            CurrencyDollar currencyDollar = new CurrencyDollar();
            CurrencyDollarAdapter currencyDollarAdapter = new CurrencyDollarAdapter(currencyDollar);
            selectedCurrency = currencyDollarAdapter;
            double price = Double.parseDouble(String.valueOf(FINAL_PRICE));
            tvPriceText.setText("Total USD to pay");
            tvTotalPriceToPay.setText(String.valueOf(showTotalPriceToPay(currencyDollarAdapter, BigDecimal.valueOf(price))));
        }
        if (item.getItemId() == R.id.currencyRON) {
            selectedCurrency = new CurrencyRon();
            tvPriceText.setText("Total RON to pay");
            tvTotalPriceToPay.setText(String.valueOf(FINAL_PRICE));
        }
        return true;
    }

    private BigDecimal showTotalPriceToPay(Currency currency, BigDecimal value){
        return currency.convertCurrency(value);
    }

    private void showPayment() {
        if(User.pay().equals("CashPayment")) {
            etCardType.setText("You selected cash payment!");
            etCardType.setEnabled(false);
            btnInsertDetailsCard.setVisibility(View.GONE);
        } else if(User.pay().equals("CryptocurrencyPayment")) {
            etCardType.setText("You selected cryptocurrency payment!");
            etCardType.setEnabled(false);
            btnInsertDetailsCard.setVisibility(View.GONE);
        } else if(User.pay().equals("MobilePayment")) {
            etCardType.setText("You selected mobile payment!");
            etCardType.setEnabled(false);
            btnInsertDetailsCard.setVisibility(View.GONE);
        } else {
            etCardType.setEnabled(true);
            btnInsertDetailsCard.setVisibility(View.VISIBLE);
        }

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
        tvPriceText = findViewById(R.id.tvPricetText);

        btnAddExtraDecorations = findViewById(R.id.btnAddExtraDecorations);
        choosenDecorations = findViewById(R.id.choosenDecorations);

        radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (radioGroup, i) -> {
            Log.d("IDRB", String.valueOf(i));
            RadioButton rb = (RadioButton) radioGroup.findViewById(i);
            if(rb.getText().toString().equals("Yes")){
                Intent intent = new Intent(CartDetailsActivity.this, CustomPackageActivity.class);
                startActivity(intent);
            }
            else {
                chosenOptions.setText("");
                btnAddExtraDecorations.setVisibility(View.GONE);
                choosenDecorations.setText("");
                decorationResult = "";
                FINAL_PRICE = totalPrice;
                tvTotalPriceToPay.setText(String.valueOf(showTotalPriceToPay(selectedCurrency, BigDecimal.valueOf(FINAL_PRICE))));
            }
        });

        tvInvoiceName.setText(LoginActivity.user.getName());
        tvInvoiceAdress.setText(LoginActivity.user.getAddress());
        FINAL_PRICE = calculateTotalPrice();
        tvTotalPriceToPay.setText(Double.toString(FINAL_PRICE));
    }

    private double calculateTotalPrice() {
        totalPrice = 0.0;
        for (ShoppingCartItem item : User.shoppingList) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
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
            btnAddExtraDecorations.setVisibility(View.GONE);
        }
        else {
            chosenOptions.setText(result);
            btnAddExtraDecorations.setVisibility(View.VISIBLE);
        }

        super.onResume();
    }


    public void actionAfterDismissDialog() {
        if (!decorationResult.equals("")) {
            choosenDecorations.setVisibility(View.VISIBLE);
            choosenDecorations.setText(decorationResult);
            tvTotalPriceToPay.setText(totalPrice + extraDecorationPrice + "");
            FINAL_PRICE = totalPrice + extraDecorationPrice;
            tvTotalPriceToPay.setText(String.valueOf(showTotalPriceToPay(selectedCurrency, BigDecimal.valueOf(FINAL_PRICE))));
        } else {
            FINAL_PRICE = totalPrice;
            tvTotalPriceToPay.setText(String.valueOf(showTotalPriceToPay(selectedCurrency, BigDecimal.valueOf(FINAL_PRICE))));
            choosenDecorations.setVisibility(View.GONE);
        }
    }
}