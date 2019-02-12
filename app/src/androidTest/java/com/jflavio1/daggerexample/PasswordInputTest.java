package com.jflavio1.daggerexample;

import android.content.Context;
import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.google.android.material.textfield.TextInputEditText;
import com.jflavio1.daggerexample.core.components.passwordTextInput.PasswordTextInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * PasswordInputTest
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
@RunWith(AndroidJUnit4.class)
public class PasswordInputTest {

    Context context;
    PasswordTextInput passwordTextInput;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        passwordTextInput = new PasswordTextInput(context, null);
    }

    @Test
    public void passwordViewAttrsTest() {
        Assert.assertEquals(LinearLayout.HORIZONTAL, passwordTextInput.getOrientation());
    }

    @Test
    public void notNullEditText() {
        EditText editText = passwordTextInput.buildEditText();
        Assert.assertNotNull(editText);
    }

    @Test
    public void verifyPinEditTextAttrsTest() {
        EditText editText = passwordTextInput.buildEditText();

        Assert.assertEquals(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15.0f,
                context.getResources().getDisplayMetrics()),
                editText.getTextSize(), 0.5);

        Assert.assertEquals(Gravity.CENTER, editText.getGravity());
        Assert.assertEquals(Color.TRANSPARENT, editText.getCurrentTextColor());
        Assert.assertFalse(editText.isLongClickable());
        Assert.assertEquals(1, editText.getMaxLines());
        Assert.assertEquals(PasswordTransformationMethod.getInstance(), editText.getTransformationMethod());
        Assert.assertFalse(editText.isClickable());
        Assert.assertFalse(editText.isTextSelectable());
    }

    @Test
    public void clearPinTest() {
        passwordTextInput.clearPin();
        for (TextInputEditText ed : passwordTextInput.getEditTexts()) {
            Assert.assertEquals(0, ed.getText().length());
        }
    }
}
