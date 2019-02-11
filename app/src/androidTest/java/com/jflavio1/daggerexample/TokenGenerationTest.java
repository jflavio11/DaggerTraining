package com.jflavio1.daggerexample;

import com.jflavio1.daggerexample.domain.repository.OTPTokenRepository;
import com.jflavio1.daggerexample.domain.repository.OTPTokenRepositoryImpl;
import com.jflavio1.daggerexample.generateOtp.ui.TokenActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TokenGenerationTest {
    private OTPTokenRepository repository = new OTPTokenRepositoryImpl();

    @Rule
    public ActivityTestRule<TokenActivity> mActivityTestRule =
            new ActivityTestRule<>(TokenActivity.class);

    @Test
    public void verifyTokenLength() throws InvalidKeyException, NoSuchAlgorithmException {
        assertEquals(6, repository.getSixDigitsToken().length());
    }

    @Test
    public void checkTextViewToken_isDisplayed_and_notEmpty() {
        onView(withId(R.id.txt_token)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.txt_token)).check(matches(not(withText("XXXXXX"))));
    }

    @Test
    public void checkTextViewToken_isRightColor() {
        onView(withId(R.id.txt_token)).check(matches(hasTextColor(R.color.design_default_color_primary_dark)));
    }

}