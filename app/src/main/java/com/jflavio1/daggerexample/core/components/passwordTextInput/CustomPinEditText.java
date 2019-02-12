package com.jflavio1.daggerexample.core.components.passwordTextInput;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import com.google.android.material.textfield.TextInputEditText;

/**
 * CustomPinEditText
 * <p>
 * CustomPinEditText is a class which provides the PIN edit text UI element for passwords. It
 * does not allow showing a virtual (native) Android keyboard and cancel any hardware keyboard
 * event if the application is installed and used on a emulator.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/12/2019
 */
public class CustomPinEditText extends TextInputEditText {

    public CustomPinEditText(Context context) {
        super(context);
    }

    public CustomPinEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPinEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        cancelPendingInputEvents();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        cancelPendingInputEvents();
        return true;
    }

    @Override
    public void setOnKeyListener(OnKeyListener l) {
        cancelPendingInputEvents();
    }
}
