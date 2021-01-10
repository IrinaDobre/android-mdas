package com.example.mpaiproject.models.designpatterns.strategy;

public class CashPaymentStrategy implements PaymentStrategy{

    @Override
    public String getTypeOfPayment() {
        return "CashPayment";
    }
}
