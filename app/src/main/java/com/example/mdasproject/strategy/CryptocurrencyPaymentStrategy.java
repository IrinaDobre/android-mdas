package com.example.mdasproject.strategy;

public class CryptocurrencyPaymentStrategy implements PaymentStrategy{

    @Override
    public String getTypeOfPayment() {
        return "CryptocurrencyPayment";
    }
}
