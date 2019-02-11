package com.jflavio1.daggerexample;

import com.jflavio1.daggerexample.generateOtp.ui.TokenActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

@RunWith(AndroidJUnit4.class)
public class TokenGenerationInstrumentedTest {
    @Rule
    public ActivityTestRule<TokenActivity> mActivityTestRule =
            new ActivityTestRule<>(TokenActivity.class);


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