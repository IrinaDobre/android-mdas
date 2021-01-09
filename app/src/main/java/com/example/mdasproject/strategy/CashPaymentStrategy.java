package com.example.mdasproject.strategy;

public class CashPaymentStrategy implements PaymentStrategy{

    @Override
    public String getTypeOfPayment() {
        return "CashPayment";
    }
}
