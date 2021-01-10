package com.example.mpaiproject.models.designpatterns.adapter;

import java.math.BigDecimal;

public class CurrencyRon implements Currency{
    @Override
    public BigDecimal convertCurrency(BigDecimal currencyValue) {
        return currencyValue;
    }
}
