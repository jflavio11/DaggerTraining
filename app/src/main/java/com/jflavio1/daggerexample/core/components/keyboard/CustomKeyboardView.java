package com.jflavio1.daggerexample.core.components.keyboard;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.jflavio1.daggerexample.core.components.expandableView.ExpandableState;
import com.jflavio1.daggerexample.core.components.expandableView.ExpandableView;
import com.jflavio1.daggerexample.core.components.keyboard.controller.DefaultKeyboardController;
import com.jflavio1.daggerexample.core.components.keyboard.controller.KeyboardController;
import com.jflavio1.daggerexample.core.components.keyboard.layout.KeyboardLayout;
import com.jflavio1.daggerexample.core.components.keyboard.layout.NumberKeyboardLayout;
import com.jflavio1.daggerexample.core.components.utils.ComponentUtils;

import java.util.HashMap;

/**
 * CustomKeyboardView
 * <p>
 * Sauce by Don.Brody, created on 7/18/18.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/7/2019
 */
public class CustomKeyboardView extends ExpandableView {

    private EditText fieldInFocus;
    private HashMap<EditText, KeyboardLayout> keyboards = new HashMap<>();
    private KeyboardListener listener;

    public CustomKeyboardView(Context context) {
        super(context);
    }

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.GRAY);

        this.listener = new KeyboardListener() {
            @Override
            public void characterClicked(char c) {
                // nothing to do here...
            }

            @Override
            public void specialKeyClicked(KeyboardController.SpecialKey key) {
                if (key == KeyboardController.SpecialKey.DONE) {
                    translateLayout();
                }

                /**
                 * NEXT key
                 * fieldInFocus?.focusSearch(View.FOCUS_DOWN)?.let {
                 it.requestFocus()
                 checkLocationOnScreen()
                 return
                 }
                 */

            }
        };

        registerListener(state -> {
            if (state == ExpandableState.EXPANDED) {
                checkLocationOnScreen();
            }
        });

        // empty onClickListener prevents user from
        // accidentally clicking views under the keyboard
        setOnClickListener(v -> {
        });
        setSoundEffectsEnabled(false);
    }

    public void registerEditText(KeyboardType keyboardType, EditText field) {
        if (!field.isEnabled()) {
            // if the field is not enable it means it does not have input connections
            return;
        }

        field.setRawInputType(InputType.TYPE_CLASS_TEXT);
        field.setTextIsSelectable(true);
        field.setShowSoftInputOnFocus(false);
        field.setSoundEffectsEnabled(false);
        field.setLongClickable(false);

        InputConnection inputConnection = field.onCreateInputConnection(new EditorInfo());

        keyboards.put(field, createKeyboardLayout(keyboardType, inputConnection));

        if (keyboards.get(field) != null) {
            keyboards.get(field).registerKeyboardListener(listener);
        }

        field.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ComponentUtils.hideSystemKeyboard(getContext(), field);

                    if (field.focusSearch(View.FOCUS_DOWN) != null) {
                        if (field.focusSearch(FOCUS_DOWN) instanceof EditText) {
                            if (keyboards.get(field) != null) {
                                keyboards.get(field).setHasNextFocus(true);
                            }
                        }
                    }

                    fieldInFocus = field;
                    renderKeyboard();
                    if (!isExpanded()) {
                        translateLayout();
                    }

                } else if (!hasFocus && isExpanded()) {
                    for (EditText editText : keyboards.keySet()) {
                        if (editText.hasFocus()) {
                            return;
                        }
                    }

                    translateLayout();
                }
            }
        });

        field.setOnClickListener(v -> {
            if (!isExpanded()) {
                translateLayout();
            }
        });

    }

    public KeyboardLayout createKeyboardLayout(KeyboardType type, InputConnection ic) {
        switch (type) {

            case NUMBER: {
                return new NumberKeyboardLayout(getContext(), false, createKeyboardController(type, ic));
            }

            default:
                return null;
        }
    }

    private KeyboardController createKeyboardController(KeyboardType type, InputConnection ic) {
        switch (type) {

            case NUMBER: {
                return new DefaultKeyboardController(ic);
            }

            default:
                return new DefaultKeyboardController(ic);
        }
    }

    private void renderKeyboard() {
        removeAllViews();
        if (fieldInFocus != null) {
            KeyboardLayout keyboardLayout = keyboards.get(fieldInFocus);
            if (keyboardLayout != null) {
                keyboardLayout.setOrientation(LinearLayout.VERTICAL);
                keyboardLayout.createKeyboard();
                addView(keyboardLayout);
            }
        }
    }

    /**
     * Check if fieldInFocus has a parent that is a ScrollView.
     * Ensure that ScrollView is enabled.
     * Check if the fieldInFocus is below the KeyboardLayout (measured on the screen).
     * If it is, find the deltaY between the top of the KeyboardLayout and the top of the
     * fieldInFocus, add 20dp (for padding), and scroll to the deltaY.
     * This will ensure the keyboard doesn't cover the field (if conditions above are met).
     */
    private void checkLocationOnScreen() {
        if (fieldInFocus == null) {
            return;
        }

        ViewParent viewParent = this.getParent();

        while (viewParent != null) {

            if (viewParent instanceof ScrollView) {

                if (!((ScrollView) viewParent).isSmoothScrollingEnabled()) {
                    break;
                }

                int[] fieldLocation = new int[2];
                this.getLocationOnScreen(fieldLocation);

                int[] keyboardLocation = new int[2];
                this.getLocationOnScreen(keyboardLocation);

                int fieldY = fieldLocation[1];
                int keyboardY = keyboardLocation[1];

                if (fieldY > keyboardY) {
                    int deltaY = fieldY - keyboardY;
                    int scrollTo = ((ScrollView) viewParent).getScrollY() + deltaY +
                            this.getMeasuredHeight() + ComponentUtils.pxToDp(getContext(), 10);
                    ((ScrollView) viewParent).smoothScrollTo(0, scrollTo);
                }
                break;
            }

            viewParent = viewParent.getParent();

        }

    }

    @Override
    protected void configureSelf() {
        renderKeyboard();
        checkLocationOnScreen();
    }

    enum KeyboardType {
        NUMBER
    }
}
