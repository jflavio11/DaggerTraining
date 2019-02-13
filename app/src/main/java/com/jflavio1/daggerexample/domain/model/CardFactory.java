package com.jflavio1.daggerexample.domain.model;

/**
 * CardFactory
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/13/2019
 */
public class CardFactory {

    public static Card createCard(CardType cardType) {

        switch (cardType) {

            case DEBIT_CARD:
                return new Card("card1", "debit basic", "", CardType.DEBIT_CARD);

            case CREDIT_CARD:
                return new Card("card2", "credit basic", "", CardType.CREDIT_CARD);

            case GOLD_DEBIT_CARD:
                return new Card("card3", "debit gold classic", "", CardType.GOLD_DEBIT_CARD);

            default:
                return new Card("card1", "debit basic", "", CardType.DEBIT_CARD);

        }

    }

}
