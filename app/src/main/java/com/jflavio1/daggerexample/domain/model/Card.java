package com.jflavio1.daggerexample.domain.model;

/**
 * Card
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/13/2019
 */
public class Card {

    private String cardId;
    private String cardName;
    private String cardNumber;
    private CardType cardType;

    Card(String cardId, String cardName, String cardNumber, CardType cardType) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
