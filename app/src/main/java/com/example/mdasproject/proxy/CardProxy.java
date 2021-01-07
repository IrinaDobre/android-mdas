package com.example.mdasproject.proxy;

import com.example.mdasproject.classes.Card;

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
        } else {
            flagCardAccepted = false;
            System.out.println("The type of selected card is not supported by this application!");
        }
    }
}
