package com.jflavio1.daggerexample.core.components.passwordTextInput;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.utils.ComponentUtils;

import java.util.ArrayList;

/**
 * BankCardInputView
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/15/2019
 */
public final class BankCardInputView extends ConstraintLayout {

    private View rootView;
    private TextInputEditText editText0, editText1, editText2, editText3;
    private int charCount, editTextPosition, MAX_CHARS = 4, MAX_EDIT_TEXTS = 3;
    private ArrayList<TextInputEditText> editTexts = new ArrayList<>();
    private OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                if (editTextPosition < MAX_EDIT_TEXTS - 1) {
                    editTexts.get(editTextPosition).requestFocus(FOCUS_FORWARD);
                }
            }
        }
    };
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            charCount++;
            if (charCount % 4 == 0) {

                if (editTextPosition < MAX_EDIT_TEXTS - 1) {
                    editTextPosition++;
                    editTexts.get(editTextPosition).requestFocus(FOCUS_FORWARD);
                } else {
                    ComponentUtils.hideSystemKeyboard(getContext(), getRootView());
                }

            }
        }
    };

    public BankCardInputView(Context context) {
        super(context);
    }

    public BankCardInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        listenToKeyboardEvents();
    }

    private void init() {
        this.rootView = inflate(getContext(), R.layout.layout_bank_card, this);
        editText0 = this.rootView.findViewById(R.id.bankCardInputLayout_til_0);
        editText1 = this.rootView.findViewById(R.id.bankCardInputLayout_til_1);
        editText2 = this.rootView.findViewById(R.id.bankCardInputLayout_til_2);
        editText3 = this.rootView.findViewById(R.id.bankCardInputLayout_til_3);

        editTexts.add(editText1);
        editTexts.add(editText2);
        editTexts.add(editText3);
    }

    private void listenToKeyboardEvents() {

        editText0.setOnClickListener(v -> {
            if (editTextPosition < MAX_EDIT_TEXTS - 1) {
                editTexts.get(editTextPosition).requestFocus(FOCUS_FORWARD);
            }
        });
        editText0.setFocusableInTouchMode(false);

        for (TextInputEditText ed :
                editTexts) {
            ed.addTextChangedListener(textWatcher);
            ed.setOnFocusChangeListener(focusChangeListener);
        }

    }

    public void setInitialEditTextValue(String val) {
        editText0.setText(val);
    }

    public String getCardBankNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(editText0.getText());
        for (TextInputEditText ed :
                editTexts) {
            stringBuilder.append(ed.getText());
        }
        return stringBuilder.toString();
    }

}
