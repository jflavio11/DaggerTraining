package com.jflavio1.daggerexample.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.keyboard.CustomKeyboardView;
import com.jflavio1.daggerexample.core.components.passwordTextInput.PasswordTextInput;
import com.jflavio1.daggerexample.core.components.utils.ComponentUtils;
import com.jflavio1.daggerexample.data.network.LocalKeyboardRepositoryImpl;
import com.jflavio1.daggerexample.domain.model.Card;
import com.jflavio1.daggerexample.domain.model.CardFactory;
import com.jflavio1.daggerexample.domain.model.CardType;
import com.jflavio1.daggerexample.login.card.CardsViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public final class LoginActivity extends AppCompatActivity implements LoginView {

    LoginPresenter presenter = new LoginPresenterImpl();

    private ConstraintLayout parentLayout;
    private ViewPager vp;
    private TabLayout tabLayout;

    private CustomKeyboardView keyboardView;
    private PasswordTextInput passwordTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        parentLayout = findViewById(R.id.loginActivity_cl_parent);
        vp = findViewById(R.id.loginActivity_vp);
        tabLayout = findViewById(R.id.loginActivity_tabLayout_indicator);

        presenter.injectView(this);
        presenter.injectKeyboardRepository(new LocalKeyboardRepositoryImpl());
        presenter.loadServerKeyboardPosition();

        setUpIu();
        setCardsViewPager();
    }

    private void setUpIu() {

    }

    public void setCardsViewPager() {

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(CardFactory.createCard(CardType.DEBIT_CARD));
        cards.add(CardFactory.createCard(CardType.GOLD_DEBIT_CARD));
        cards.add(CardFactory.createCard(CardType.CREDIT_CARD));

        FragmentStatePagerAdapter adapter = new CardsViewPagerAdapter(getSupportFragmentManager(), cards);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (keyboardView.theViewIsExpanded()) {
            passwordTextInput.clearPin();
            keyboardView.translateLayout();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onKeyboardPositionsLoaded(List<String> positions) {
        keyboardView = findViewById(R.id.loginActivity_keyboard);
        passwordTextInput = findViewById(R.id.loginActivity_pti);
        passwordTextInput.setMAX_EDIT_TEXTS(4);
        passwordTextInput.setKeyboardView(keyboardView, (ArrayList) positions);

        keyboardView.registerListener(state -> {

            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) passwordTextInput.getLayoutParams();

            switch (state) {

                case EXPANDING: {
                    params.bottomToTop = keyboardView.getId();
                    params.bottomMargin = ComponentUtils.dpToPx(this, 20);
                    break;
                }

                case COLLAPSING: {
                    params.bottomToBottom = -1;
                    params.bottomToTop = -1;
                }

            }

            passwordTextInput.requestLayout();
        });

    }
}
