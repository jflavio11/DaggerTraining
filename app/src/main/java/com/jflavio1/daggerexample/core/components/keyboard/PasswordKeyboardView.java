package com.jflavio1.daggerexample.core.components.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * PasswordKeyboardView
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public class PasswordKeyboardView extends CustomKeyboardView {

    public PasswordKeyboardView(Context context) {
        super(context);
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void registerEditText(KeyboardType keyboardType, EditText editText) {
        super.registerEditText(keyboardType, editText);
    }
}
