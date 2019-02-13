package com.jflavio1.daggerexample.login.card;

import com.jflavio1.daggerexample.domain.model.CardType;

/**
 * IBankCardDrawer
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/13/2019
 */
public interface IBankCardDrawer {
    int getBankCardResourceId(CardType cardType);
}
