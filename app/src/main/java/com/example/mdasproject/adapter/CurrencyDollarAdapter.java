package com.example.mdasproject.adapter;

import java.math.BigDecimal;

public class CurrencyDollarAdapter implements Currency{

    CurrencyDollar currency;

    public CurrencyDollarAdapter(CurrencyDollar currency) {
        this.currency = currency;
    }

    @Override
    public BigDecimal convertCurrency(BigDecimal currencyValue) {
        return currency.convertCurrencyToUSD(currencyValue);
    }
}
