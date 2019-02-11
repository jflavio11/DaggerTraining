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
import androidx.annotation.Nullable;
import com.jflavio1.daggerexample.core.components.expandableView.ExpandableState;
import com.jflavio1.daggerexample.core.components.expandableView.ExpandableView;
import com.jflavio1.daggerexample.core.components.keyboard.controller.DefaultKeyboardController;
import com.jflavio1.daggerexample.core.components.keyboard.controller.KeyboardController;
import com.jflavio1.daggerexample.core.components.keyboard.layout.KeyboardLayout;
import com.jflavio1.daggerexample.core.components.keyboard.layout.PasswordNumberKeyboardLayout;
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
                } else if (key == KeyboardController.SpecialKey.HIDE_KEYBOARD) {
                    translateLayout();
                }
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

    public void setListener(KeyboardListener listener) {
        this.listener = listener;
    }

    protected void setFieldInFocus(EditText fieldInFocus) {
        this.fieldInFocus = fieldInFocus;
    }

    protected void createInputConnectionToEditText(KeyboardType keyboardType, EditText editText) {
        if (!editText.isEnabled()) {
            // if the field is not enable it means it does not have input connections
            return;
        }

        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        editText.setShowSoftInputOnFocus(false);
        editText.setSoundEffectsEnabled(false);
        editText.setLongClickable(false);

        InputConnection inputConnection = editText.onCreateInputConnection(new EditorInfo());

        keyboards.put(editText, createKeyboardLayout(keyboardType, inputConnection));

        if (keyboards.get(editText) != null) {
            keyboards.get(editText).registerKeyboardListener(listener);
        }

    }

    /**
     * RegisterEditText is a method that, given a keyboardType and a editText ui instance, will
     * create an in-app keyboard layout and register the
     * {@link android.view.View.OnFocusChangeListener} and
     * {@link android.view.View.OnClickListener} callbacks for the edit text.
     * <p>
     * This method can be override for custom configurations. For example, for banking password
     * where input edit texts are together in the same line, the focus search can call the
     * {@link KeyboardLayout#setHasNextFocus(boolean)} for the edit text that is
     * {@link View#FOCUS_RIGHT}.
     * <p>
     * If the edit text loss focus, the algorithm will search for any other edit text registered
     * in the {@link #keyboards} and if any of them {@link EditText#hasFocus()} then the keyboard
     * view must be still shown, so <code>return;</code> is invoked and
     * {@link #translateLayout()} is not called.
     *
     * @param keyboardType The keyboard type for the edit text.
     * @param editText     The edit text instance for showing the keyboard.
     */
    public void registerEditText(KeyboardType keyboardType, EditText editText) {
        createInputConnectionToEditText(keyboardType, editText);
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ComponentUtils.hideSystemKeyboard(getContext(), editText);

                // if we can find a view below this field, we want to replace the
                // done button with the next button in the attached keyboard
                if (editText.focusSearch(View.FOCUS_DOWN) != null) {
                    if (editText.focusSearch(FOCUS_DOWN) instanceof EditText) {
                        if (keyboards.get(editText) != null) {
                            keyboards.get(editText).setHasNextFocus(true);
                        }
                    }
                }

                fieldInFocus = editText;
                renderKeyboard();
                if (!theViewIsExpanded()) {
                    translateLayout();
                }

            } else if (theViewIsExpanded()) {
                for (EditText et : keyboards.keySet()) {
                    if (et.hasFocus()) {
                        return;
                    }
                }

                translateLayout();
            }
        });

        editText.setOnClickListener(v -> {
            if (!theViewIsExpanded()) {
                translateLayout();
            }
        });

    }

    public void registerEditText(KeyboardType keyboardType, EditText editText, Runnable runnable) {
        createInputConnectionToEditText(keyboardType, editText);
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ComponentUtils.hideSystemKeyboard(getContext(), editText);

                // if we can find a view below this field, we want to replace the
                // done button with the next button in the attached keyboard
                if (editText.focusSearch(View.FOCUS_DOWN) != null) {
                    if (editText.focusSearch(FOCUS_DOWN) instanceof EditText) {
                        if (keyboards.get(editText) != null) {
                            keyboards.get(editText).setHasNextFocus(true);
                        }
                    }
                }

                fieldInFocus = editText;
                renderKeyboard();
                if (!theViewIsExpanded()) {
                    translateLayout();
                }

                if (runnable != null) {
                    runnable.run();
                }

            } else if (theViewIsExpanded()) {
                for (EditText et : keyboards.keySet()) {
                    if (et.hasFocus()) {
                        return;
                    }
                }

                translateLayout();
            }
        });

        setEditTextClickListener(editText, null);

    }

    public void setEditTextClickListener(EditText editText,
                                         @Nullable OnClickListener clickListener) {
        if (clickListener == null) {
            editText.setOnClickListener(v -> {
                if (!theViewIsExpanded()) {
                    translateLayout();
                }
            });
        } else {
            editText.setOnClickListener(clickListener);
        }
    }

    public KeyboardLayout createKeyboardLayout(KeyboardType type, InputConnection ic) {
        switch (type) {

            case BANK_PASSWORD: {
                PasswordNumberKeyboardLayout keyboardLayout =
                        new PasswordNumberKeyboardLayout(getContext(), false, createKeyboardController(type, ic));
                if (type.getServerKeyValues() != null) {
                    keyboardLayout.setServerKeyValues(type.getServerKeyValues());
                } else {
                    throw new RuntimeException("Keyboard type: BANK_PASSWORD must" +
                            "have serverKeyValues array");
                }
                return keyboardLayout;
            }

            default:
                return null;
        }
    }

    private KeyboardController createKeyboardController(KeyboardType type, InputConnection ic) {
        switch (type) {

            case BANK_PASSWORD: {
                return new DefaultKeyboardController(ic);
            }

            default:
                return new DefaultKeyboardController(ic);
        }
    }

    protected void renderKeyboard() {
        removeAllViews();
        if (fieldInFocus != null) {
            KeyboardLayout keyboardLayout = keyboards.get(fieldInFocus);
            if (keyboardLayout != null) {
                keyboardLayout.setOrientation(LinearLayout.VERTICAL);
                keyboardLayout.createKeyboard((float) getMeasuredWidth());
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

}
