package com.jflavio1.daggerexample.login.card;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.domain.model.CardType;

/**
 * BankCardDrawer
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/13/2019
 */
public class BankCardDrawer implements IBankCardDrawer {

    @Override
    public int getBankCardResourceId(CardType cardType) {
        switch (cardType) {
            case DEBIT_CARD:
                return R.drawable.visacard_front;

            case CREDIT_CARD:
                return R.drawable.visacard_back;

            case GOLD_DEBIT_CARD:
                return R.drawable.visacard_gold_front;

            default:
                return R.drawable.visacard_front;
        }
    }

}
