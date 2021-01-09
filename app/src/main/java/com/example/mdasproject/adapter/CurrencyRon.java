package com.example.mdasproject.adapter;

import java.math.BigDecimal;

public class CurrencyRon implements Currency{
    @Override
    public BigDecimal convertCurrency(BigDecimal currencyValue) {
        return currencyValue;
    }
}
