package com.example.mpaiproject.models.designpatterns.strategy;

public class CryptocurrencyPaymentStrategy implements PaymentStrategy{

    @Override
    public String getTypeOfPayment() {
        return "CryptocurrencyPayment";
    }
}
