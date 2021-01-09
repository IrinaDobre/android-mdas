package com.example.mdasproject.strategy;

import android.content.Context;
import android.widget.Toast;

import com.example.mdasproject.CartDetailsActivity;

public class MobilePaymentStrategy implements PaymentStrategy{
    @Override
    public String getTypeOfPayment() {
        return "MobilePayment";
    }
}
