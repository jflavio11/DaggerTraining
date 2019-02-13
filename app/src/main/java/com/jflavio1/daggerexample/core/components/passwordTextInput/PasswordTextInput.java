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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.material.textfield.TextInputEditText;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.keyboard.CustomKeyboardView;
import com.jflavio1.daggerexample.core.components.keyboard.KeyboardListener;
import com.jflavio1.daggerexample.core.components.keyboard.KeyboardType;
import com.jflavio1.daggerexample.core.components.keyboard.controller.KeyboardController;
import timber.log.Timber;

import java.util.ArrayList;

/**
 * PasswordTextInput
 * <p>
 * This class extends from {@link LinearLayout} for adding {@link TextInputEditText} on a
 * horizontal way. These edit texts are going to be used a PIN password view. A dependency with a
 * {@link CustomKeyboardView} is necessary in order to handle secure keyboard events.
 * <p>
 * Each edit text is created on the {@link #buildEditText()} method with the necessary attributes
 * for a PIN password view. We set the text color as {@link Color#TRANSPARENT} in order to not
 * allow showing the password character and when a key is pressed on the {@link #keyboardView}
 * the edit text background defined as {@link R.drawable#bg_password_input} is changed to
 * {@link R.drawable#bg_password_input_typed}.
 *
 * <p>
 * <br>
 * TODO: hide Operating system keyboard that is being shown when emulator is used.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/8/2019
 */
public class PasswordTextInput extends LinearLayout {

    private CustomKeyboardView keyboardView;
    private static final int PIN_CHAR_MAX_LENGTH = 0;
    private int MAX_PIN_LENGTH = 6;
    private ArrayList<TextInputEditText> editTexts = new ArrayList<>();
    private int pinPosition = 0;

    // TODO: verify if this must be an array
    private StringBuilder finalPinKeyboardPositions = new StringBuilder();

    private OnClickListener editTextClickListener = v -> {
        if (pinPosition < MAX_PIN_LENGTH && !keyboardView.theViewIsExpanded()) {
            editTexts.get(pinPosition).setCursorVisible(true);
            if (keyboardView != null) {
                keyboardView.translateLayout();
            }
        } else {
            clearPin();
        }
    };

    private Runnable editTextFocusRunnable = () -> {
        if (pinPosition >= MAX_PIN_LENGTH) {
            clearPin();
        } else {
            editTexts.get(pinPosition).setCursorVisible(true);
            editTexts.get(pinPosition).requestFocus();
        }
    };

    private OnFocusChangeListener editTextFocusListener = (v, hasFocus) -> {
        if (hasFocus) {
            editTextFocusRunnable.run();
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
        buildUi();
    }

    public void setMAX_PIN_LENGTH(int MAX_PIN_LENGTH) {
        this.MAX_PIN_LENGTH = MAX_PIN_LENGTH;
        removeAllViews();
        buildUi();
    }

    /**
     * setKeyboardView is a method that, given a CustomKeyboardView and a ArrayList containing
     * the keyboard key positions, will handle the special keyboard events for this custom
     * password view.
     *
     * @param keyboardView  The custom in-app keyboard view that will handle keyboard events.
     * @param charPositions The ArrayList of keyboard keys in a random order.
     */
    public void setKeyboardView(@NonNull CustomKeyboardView keyboardView,
                                @NonNull ArrayList<String> charPositions) {
        this.keyboardView = keyboardView;
        this.keyboardView.setListener(new KeyboardListener() {
            @Override
            public void characterClicked(char c) {
                if (pinPosition < MAX_PIN_LENGTH) {
                    editTexts.get(pinPosition).setBackgroundResource(R.drawable.bg_password_input_typed);
                    editTexts.get(pinPosition).setCursorVisible(false);
                    finalPinKeyboardPositions.append(c);
                }
                pinPosition++;
                if (pinPosition < MAX_PIN_LENGTH) {
                    editTexts.get(pinPosition).requestFocus();
                } else {
                    Timber.i("Pin password: " + finalPinKeyboardPositions);
                    keyboardView.translateLayout();
                }
            }

            @Override
            public void specialKeyClicked(KeyboardController.SpecialKey key) {
                if (key == KeyboardController.SpecialKey.DELETE_ALL) {
                    clearPin();
                }
            }
        });

        for (TextInputEditText editText : editTexts) {
            KeyboardType type = KeyboardType.BANK_PASSWORD;
            type.setServerKeyValues(charPositions);
            this.keyboardView.registerEditText(type, editText, editTextFocusRunnable);
            this.keyboardView.setEditTextClickListener(editText, editTextClickListener);
        }

    }

    /**
     * buildUi will create and add to the screen all the edit texts with a maximum of
     * {@link #MAX_PIN_LENGTH} views.
     */
    private void buildUi() {
        for (int i = 0; i < MAX_PIN_LENGTH; i++) {
            TextInputEditText editText = buildEditText();
            editText.addTextChangedListener(textWatcher);
            editText.setOnFocusChangeListener(editTextFocusListener);
            editTexts.add(editText);
            addView(editText);
        }
    }

    /**
     * clearPin will clear all edit texts that compose the PIN password view and will request
     * focus to the first edit text.
     */
    public void clearPin() {
        finalPinKeyboardPositions = new StringBuilder();
        for (TextInputEditText editText : editTexts) {
            if (editText.getText() != null) {
                editText.getText().clear();
            }
            editText.setBackgroundResource(R.drawable.bg_password_input);
        }
        pinPosition = 0;
        editTexts.get(pinPosition).requestFocus();
    }

    /**
     * buildEditText is a method that will create an instance of {@link TextInputEditText} with
     * attributes as a PIN password edit text view. This means, that the input type must be
     * {@link InputType#TYPE_TEXT_VARIATION_PASSWORD}, must have 1 line, text gravity must be
     * centered and text selection is disable.
     *
     * @return A PIN password custom edit text.
     */
    public CustomPinEditText buildEditText() {
        CustomPinEditText textInputEditText = new CustomPinEditText(getContext());
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

    @RestrictTo(RestrictTo.Scope.TESTS)
    public ArrayList<TextInputEditText> getEditTexts() {
        return editTexts;
    }
}
