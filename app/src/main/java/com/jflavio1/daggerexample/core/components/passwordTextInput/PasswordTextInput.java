package com.jflavio1.daggerexample.core.components.passwordTextInput;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.*;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.jflavio1.daggerexample.R;

import java.util.ArrayList;

/**
 * PasswordTextInput
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/8/2019
 */
public class PasswordTextInput extends LinearLayout {

    private static final int PIN_CHAR_MAX_LENGTH = 1;
    private static final int MAX_PIN_LENGTH = 6;
    private ArrayList<TextInputEditText> editTexts = new ArrayList<>();
    private int pinPosition = 0;
    private OnClickListener containerClickListener = v -> {

    };
    private OnFocusChangeListener editTextFocusListener = (v, hasFocus) -> {
        if (hasFocus) {
            editTexts.get(pinPosition).setCursorVisible(true);
            if (pinPosition == 0) {
                editTexts.get(pinPosition).requestFocus();
            } else if (pinPosition == MAX_PIN_LENGTH) {
                clearPin();
            }
            // TODO keyboardview must be shown or hide
        }
    };
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (pinPosition < MAX_PIN_LENGTH) {
                editTexts.get(pinPosition).setBackgroundResource(R.drawable.bg_password_input_typed);
                editTexts.get(pinPosition).setCursorVisible(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            pinPosition++;
            if (pinPosition < MAX_PIN_LENGTH) {
                editTexts.get(pinPosition).requestFocus();
            } else {
                pinPosition = 5;
            }
        }
    };

    public PasswordTextInput(Context context) {
        super(context);
    }

    public PasswordTextInput(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayoutParams(
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        setGravity(TEXT_ALIGNMENT_CENTER);
        setOrientation(HORIZONTAL);
        this.setOnClickListener(containerClickListener);
        buildUi();
    }

    private void buildUi() {

        for (int i = 0; i < MAX_PIN_LENGTH; i++) {
            editTexts.add(buildEditText());
        }

        for (TextInputEditText editText : editTexts) {
            editText.addTextChangedListener(textWatcher);
            editText.setOnFocusChangeListener(editTextFocusListener);
            addView(editText);
        }
    }

    public void clearPin() {
        for (TextInputEditText editText : editTexts) {
            if (editText.getText() != null) {
                editText.getText().clear();
            }
            editText.setBackgroundResource(R.drawable.bg_password_input);
        }
        pinPosition = 0;
        editTexts.get(pinPosition).requestFocus();
    }

    public TextInputEditText buildEditText() {
        TextInputEditText textInputEditText = new TextInputEditText(getContext());
        LayoutParams layoutParams = new LayoutParams(
                Math.round(getContext().getResources().getDimension(R.dimen.custom_editText_password_width)),
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(
                getResources().getDimensionPixelSize(R.dimen.custom_editText_password_marginStart),
                getResources().getDimensionPixelSize(R.dimen.custom_editText_password_marginTop),
                getResources().getDimensionPixelSize(R.dimen.custom_editText_password_marginEnd),
                getResources().getDimensionPixelSize(R.dimen.custom_editText_password_marginBottom)
        );
        textInputEditText.setLayoutParams(layoutParams);
        textInputEditText.setTextSize(15.0f);
        textInputEditText.setGravity(Gravity.CENTER);
        textInputEditText.setBackgroundResource(R.drawable.bg_password_input);
        textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textInputEditText.setLongClickable(false);
        textInputEditText.setMaxLines(1);
        textInputEditText.setTextColor(Color.TRANSPARENT);
        textInputEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        textInputEditText.setFilters((new InputFilter[]{new InputFilter.LengthFilter(PIN_CHAR_MAX_LENGTH)}));
        textInputEditText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textInputEditText.setClickable(false);
        textInputEditText.setTextIsSelectable(false);
        textInputEditText.setSelectAllOnFocus(false);
        // prevents the copy/paste/selection feature of the edit text
        textInputEditText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });
        return textInputEditText;
    }

}
