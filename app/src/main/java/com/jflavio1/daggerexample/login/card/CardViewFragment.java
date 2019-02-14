package com.jflavio1.daggerexample.login.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.domain.model.Card;
import com.jflavio1.daggerexample.domain.model.CardFactory;
import com.jflavio1.daggerexample.domain.model.CardType;

/**
 * CardViewFragment
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/13/2019
 */
public class CardViewFragment extends Fragment {

    private Card card;

    public static CardViewFragment newInstance(Card card) {
        CardViewFragment cardViewFragment = new CardViewFragment();
        Bundle args = new Bundle();
        args.putString("cardType", card.getCardType().name());
        cardViewFragment.setArguments(args);
        return cardViewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            card = CardFactory.createCard(CardType.valueOf(getArguments().getString("cardType")));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IBankCardDrawer cardDrawer = new BankCardDrawer();
        int resId = cardDrawer.getBankCardResourceId(this.card.getCardType());
        ((ImageView) view.findViewById(R.id.cardViewFragment_iv_background)).setImageResource(resId);

    }
}
