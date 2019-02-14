package com.jflavio1.daggerexample.login.card;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.jflavio1.daggerexample.domain.model.Card;

import java.util.List;

/**
 * CardsViewPagerAdapter
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/13/2019
 */
public class CardsViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Card> cardList;

    public CardsViewPagerAdapter(FragmentManager fm, List<Card> cardList) {
        super(fm);
        this.cardList = cardList;
    }

    @Override
    public Fragment getItem(int position) {
        return CardViewFragment.newInstance(this.cardList.get(position));
    }

    @Override
    public int getCount() {
        return cardList.size();
    }
}
