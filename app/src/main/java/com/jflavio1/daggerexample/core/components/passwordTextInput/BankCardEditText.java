package com.jflavio1.daggerexample.core.components.passwordTextInput;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.utils.ComponentUtils;

/**
 * BankCardEditText
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/14/2019
 */
public class BankCardEditText extends BankTextInput {

    private int editTextSelectedPosition = 0;
    private int cursorPosition = 1;

    public BankCardEditText(Context context) {
        super(context);
    }

    public BankCardEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        MAX_EDIT_TEXTS = 3;
        PIN_CHAR_MAX_LENGTH = 4;
        buildUi();
    }

    @Override
    protected void buildUi() {
        editTexts.clear();
        for (int i = 0; i < MAX_EDIT_TEXTS; i++) {
            TextInputEditText editText = buildEditText();
            editText.addTextChangedListener(editTextTextWatcher);
            editText.setOnFocusChangeListener(editTextFocusListener);
            editTexts.add(editText);
            addView(editText);
        }
    }

    @Override
    protected TextInputEditText buildEditText() {
        TextInputEditText textInputEditText = new TextInputEditText(getContext(), null,
                R.style.Widget_MaterialComponents_TextInputLayout_FilledBox);
        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.25f
        );
        textInputEditText.setPadding(0,
                getResources().getDimensionPixelSize(R.dimen.custom_editText_password_marginTop),
                0,
                getResources().getDimensionPixelSize(R.dimen.custom_editText_password_marginBottom)
        );
        textInputEditText.setLayoutParams(layoutParams);
        textInputEditText.setGravity(Gravity.CENTER);
        textInputEditText.setBackgroundResource(R.drawable.bg_bank_card_number);
        textInputEditText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        textInputEditText.setLongClickable(false);
        textInputEditText.setMaxLines(1);
        textInputEditText.setEnabled(true);
        textInputEditText.setText("asd");
        textInputEditText.setCursorVisible(true);
        textInputEditText.setFilters((new InputFilter[]{new InputFilter.LengthFilter(PIN_CHAR_MAX_LENGTH)}));
        textInputEditText.setRawInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        textInputEditText.setTextIsSelectable(false);
        textInputEditText.setSelectAllOnFocus(false);
        return textInputEditText;
    }

    @Override
    protected void defineEditTextClickListener() {
        editTextClickListener = v -> {
            editTexts.get(editTextSelectedPosition).requestFocus();
        };
    }

    @Override
    protected void defineEditTextFocusListener() {
        editTextFocusListener = (v, hasFocus) -> {
            if (hasFocus) {
                editTexts.get(editTextSelectedPosition).requestFocus();
            }
        };
    }

    @Override
    protected void defineEditTextWatcher() {
        editTextTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextSelectedPosition > MAX_EDIT_TEXTS) {
                    ComponentUtils.hideSystemKeyboard(getContext(), getRootView());
                } else {
                    cursorPosition++;
                    if (cursorPosition % 4 == 0) {
                        editTextSelectedPosition++;
                    }
                }
            }
        };
    }
}
