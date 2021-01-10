package com.example.mpaiproject.models;

import com.example.mpaiproject.models.designpatterns.proxy.CardProxy;
import com.example.mpaiproject.models.designpatterns.proxy.ICard;

public class Card implements ICard {

    public static final String TYPE_MASTERCARD = "MASTERCARD";
    public static final String TYPE_VISA = "VISA";

    private String cardType;
    private long cardNumber;
    private int CVV;
    private String validDate;
    private String cardHolder;

    public Card() {
    }

    public Card(String cardType, long cardNumber, int CVV, String validDate, String cardHolder) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.validDate = validDate;
        this.cardHolder = cardHolder;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public void selectCard(String cardType) {
        CardProxy.flagCardAccepted = true;
        System.out.println("The type of selected card is: " + cardType);
    }
}
