package com.jflavio1.daggerexample.core.components.passwordTextInput;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.jflavio1.daggerexample.core.components.keyboard.CustomKeyboardView;

import java.util.ArrayList;

/**
 * BankTextInput
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/14/2019
 */
public abstract class BankTextInput extends LinearLayout {

    protected CustomKeyboardView keyboardView;
    protected int PIN_CHAR_MAX_LENGTH = 0;
    protected int MAX_EDIT_TEXTS = 6;
    protected ArrayList<TextInputEditText> editTexts = new ArrayList<>();

    protected OnClickListener editTextClickListener;
    protected OnFocusChangeListener editTextFocusListener;
    protected TextWatcher editTextTextWatcher;

    public BankTextInput(Context context) {
        super(context);
    }

    public BankTextInput(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayoutParams(
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        defineEditTextClickListener();
        defineEditTextFocusListener();
        defineEditTextWatcher();
        setGravity(TEXT_ALIGNMENT_CENTER);
        setOrientation(HORIZONTAL);
    }

    public void setMAX_EDIT_TEXTS(int MAX_EDIT_TEXTS) {
        this.MAX_EDIT_TEXTS = MAX_EDIT_TEXTS;
    }

    protected abstract void buildUi();

    public void clearField() {
        for (TextInputEditText editText : editTexts) {
            if (editText.getText() != null) {
                editText.getText().clear();
            }
        }
    }

    protected abstract TextInputEditText buildEditText();

    protected abstract void defineEditTextClickListener();

    protected abstract void defineEditTextFocusListener();

    protected abstract void defineEditTextWatcher();

}
