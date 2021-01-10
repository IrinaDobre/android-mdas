package com.example.mpaiproject.models.designpatterns.strategy;

public class CardPaymentStrategy implements PaymentStrategy{
    @Override
    public String getTypeOfPayment() {
        return "CardPayment";
    }
}
