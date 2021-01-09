package com.example.mdasproject.adapter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyEuro implements Currency{
    @Override
    public BigDecimal convertCurrency(BigDecimal currencyValue) {
        return currencyValue.divide(BigDecimal.valueOf(4.87), 2, RoundingMode.FLOOR);
    }
}
