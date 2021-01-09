package com.example.mdasproject.strategy;

public class CardPaymentStrategy implements PaymentStrategy{
    @Override
    public String getTypeOfPayment() {
        return "CardPayment";
    }
}
