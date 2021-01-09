package com.example.mdasproject.proxy;

import android.util.Log;

import com.example.mdasproject.models.Card;

public class CardProxy implements ICard {

    private ICard card;
    public static boolean flagCardAccepted;

    public CardProxy(ICard card) {
        this.card = card;
    }

    @Override
    public void selectCard(String cardType) {
        if (cardType.equalsIgnoreCase(Card.TYPE_MASTERCARD) || cardType.equalsIgnoreCase(Card.TYPE_VISA)) {
            card.selectCard(cardType);
            Log.d("DESIGN_PATTERN_PROXY", cardType);
        } else {
            flagCardAccepted = false;
            Log.d("DESIGN_PATTERN_PROXY", "The type of selected card is not supported by this application!");
        }
    }
}
