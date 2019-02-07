package com.jflavio1.daggerexample.core.components.keyboard.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jflavio1.daggerexample.core.components.keyboard.controller.KeyboardController;
import com.jflavio1.daggerexample.core.components.utils.ComponentUtils;

import java.util.List;

/**
 * KeyboardLayout
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/7/2019
 */
public abstract class KeyboardLayout extends LinearLayout {

    private boolean hasNextFocus;
    private KeyboardController keyboardController;
    private float screenWidth = 0.0f;
    protected float textSize = 20.0f;

    public KeyboardLayout(Context context) {
        super(context);
    }

    public KeyboardLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setLayoutParams(
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
    }

    public void createKeyboard() {
        removeAllViews();
        LinearLayout keyboardLayout = createWrapperLayout();
        for (LinearLayout rowLl : createRows()) {
            keyboardLayout.addView(rowLl);
        }
        addView(keyboardLayout);
    }

    /**
     * Creates a button UI element with the given <code>textContent</code> and with a width
     * measured as a percent value of the device screen width.
     *
     * @param textContent            Text content of the button.
     * @param widthAsPercentOfScreen Value that indicates the width of the button as a percent of
     *                               the screen size.
     * @return a button ui instance.
     */
    private Button createButton(String textContent, float widthAsPercentOfScreen) {
        Button button = new Button(getContext());
        button.setLayoutParams(
                new LayoutParams(
                        Math.round(screenWidth * widthAsPercentOfScreen),
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        ComponentUtils.setBackgroundTint(button, Color.LTGRAY);
        button.setAllCaps(false);
        button.setText(textContent);
        button.setTextSize(textSize);
        return button;
    }

    protected Button createButton(String textContent, float widthAsPercentOfScreen, Character c) {
        Button button = createButton(textContent, widthAsPercentOfScreen);
        button.setOnClickListener(v -> {
            if (keyboardController != null) {
                keyboardController.onKeyStroke(c);
            }
        });
        return button;
    }

    protected Button createButton(String textContent, float widthAsPercentOfScreen, KeyboardController.SpecialKey specialKey) {
        Button button = createButton(textContent, widthAsPercentOfScreen);
        button.setOnClickListener(v -> {
            if (keyboardController != null) {
                keyboardController.onKeyStroke(specialKey);
            }
        });
        return button;
    }

    /**
     * Creates a row ui element for the keyboard. This layout is going to display the buttons of
     * the current row.
     *
     * @param buttons Buttons that must be added to the row.
     * @return a row as a horizontal Linear Layout.
     */
    protected LinearLayout createKeyboardRow(List<View> buttons) {
        LinearLayout row = new LinearLayout(getContext());
        row.setLayoutParams(
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        row.setOrientation(HORIZONTAL);
        row.setGravity(Gravity.CENTER);
        for (View btn : buttons) {
            row.addView(btn);
        }
        return row;
    }

    /**
     * Creates the linear layout for showing each key.
     *
     * @return The linear layout of a keyboard row.
     */
    private LinearLayout createWrapperLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams llLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llLp.topMargin = ComponentUtils.pxToDp(getContext(), integerToDp(15));
        llLp.bottomMargin = ComponentUtils.pxToDp(getContext(), integerToDp(15));
        linearLayout.setLayoutParams(llLp);
        linearLayout.setOrientation(VERTICAL);
        return linearLayout;
    }

    private int integerToDp(int i) {
        return Math.round(i / Resources.getSystem().getDisplayMetrics().density);
    }

    protected abstract List<LinearLayout> createRows();

}
