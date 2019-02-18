package com.jflavio1.daggerexample;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.jflavio1.daggerexample.core.components.passwordTextInput.BankCardInputView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * BankCardInputViewTest
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/18/2019
 */
@RunWith(AndroidJUnit4.class)
public class BankCardInputViewTest {

    Context context;
    BankCardInputView inputView;
    InputMethodManager imm;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        inputView = new BankCardInputView(context, null);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Test
    public void nextEditTextFocusedTest() {
        inputView.getEditTexts().get(0).setText("1234");
        Assert.assertTrue(inputView.getEditTexts().get(1).isFocused());

        inputView.getEditTexts().get(1).setText("1234");
        Assert.assertTrue(inputView.getEditTexts().get(2).isFocused());

    }

    @Test
    public void keyboardHiddenTest() {
        inputView.getEditTexts().get(0).setText("1234");
        inputView.getEditTexts().get(1).setText("1234");
        inputView.getEditTexts().get(2).setText("1234");
        Assert.assertFalse(imm.isAcceptingText());
    }

    @Test
    public void previousEditTextFocusTest() {
        inputView.getEditTexts().get(0).setText("1234");
        inputView.getEditTexts().get(1).setText("1234");
        inputView.getEditTexts().get(2).setText("1234");

        inputView.getEditTexts().get(2).setText("");
        Assert.assertFalse(inputView.getEditTexts().get(2).isFocused());
        Assert.assertTrue(inputView.getEditTexts().get(1).isFocused());

        inputView.getEditTexts().get(1).setText("");
        Assert.assertFalse(inputView.getEditTexts().get(1).isFocused());
        Assert.assertTrue(inputView.getEditTexts().get(0).isFocused());
    }

    @Test
    public void getCardBankNumberTest() {
        inputView.setInitialEditTextValue("1234");
        inputView.getEditTexts().get(0).setText("1234");
        inputView.getEditTexts().get(1).setText("1234");
        inputView.getEditTexts().get(2).setText("1234");
        Assert.assertEquals("1234123412341234", inputView.getCardBankNumber());
    }
}
