package com.example.mdasproject.adapter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyDollar {

    public BigDecimal convertCurrencyToUSD(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(3.98)).setScale(2, RoundingMode.FLOOR);
    }
}
