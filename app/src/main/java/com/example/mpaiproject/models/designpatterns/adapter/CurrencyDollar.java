package com.example.mpaiproject.models.designpatterns.adapter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyDollar {

    public BigDecimal convertCurrencyToUSD(BigDecimal value) {
        return value.divide(BigDecimal.valueOf(3.98), 2, RoundingMode.FLOOR);
    }
}
