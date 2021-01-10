package com.example.mpaiproject.models.designpatterns.adapter;

import java.math.BigDecimal;

public interface Currency {

    BigDecimal convertCurrency(BigDecimal currencyValue);
}
