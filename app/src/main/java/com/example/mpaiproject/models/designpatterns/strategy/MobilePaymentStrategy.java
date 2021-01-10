package com.example.mpaiproject.models.designpatterns.strategy;

public class MobilePaymentStrategy implements PaymentStrategy{
    @Override
    public String getTypeOfPayment() {
        return "MobilePayment";
    }
}
