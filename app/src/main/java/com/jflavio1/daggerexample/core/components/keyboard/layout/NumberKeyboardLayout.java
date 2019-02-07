package com.jflavio1.daggerexample.core.components.keyboard.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jflavio1.daggerexample.core.components.keyboard.controller.KeyboardController;

import java.util.ArrayList;
import java.util.List;

/**
 * NumberKeyboardLayout
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/7/2019
 */
public class NumberKeyboardLayout extends KeyboardLayout {

    private String[] serverKeyValues = new String[10];

    public NumberKeyboardLayout(Context context) {
        super(context);
    }

    public NumberKeyboardLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberKeyboardLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setServerKeyValues(String[] serverKeyValues) {
        this.serverKeyValues = serverKeyValues;
    }

    @Override
    protected List<LinearLayout> createRows() {

        // since there are going to be 3 keys, they represent the 33% of the screen
        float columnWidth = 0.3333f;
        textSize = 22.0f;

        ArrayList<View> rowOne = new ArrayList<>();
        rowOne.add(createButton(serverKeyValues[0], columnWidth, '0'));
        rowOne.add(createButton(serverKeyValues[1], columnWidth, '1'));
        rowOne.add(createButton(serverKeyValues[2], columnWidth, '2'));

        ArrayList<View> rowTwo = new ArrayList<>();
        rowTwo.add(createButton(serverKeyValues[3], columnWidth, '3'));
        rowTwo.add(createButton(serverKeyValues[4], columnWidth, '4'));
        rowTwo.add(createButton(serverKeyValues[5], columnWidth, '5'));

        ArrayList<View> rowThree = new ArrayList<>();
        rowThree.add(createButton(serverKeyValues[6], columnWidth, '6'));
        rowThree.add(createButton(serverKeyValues[7], columnWidth, '7'));
        rowThree.add(createButton(serverKeyValues[8], columnWidth, '8'));

        ArrayList<View> rowFour = new ArrayList<>();
        rowThree.add(createButton("", columnWidth, KeyboardController.SpecialKey.NONE));
        rowThree.add(createButton(serverKeyValues[9], columnWidth, '9'));
        rowThree.add(createButton("⌫", columnWidth, KeyboardController.SpecialKey.DELETE_ALL));

        ArrayList<LinearLayout> keyboardRows = new ArrayList<>();
        keyboardRows.add(createKeyboardRow(rowOne));
        keyboardRows.add(createKeyboardRow(rowTwo));
        keyboardRows.add(createKeyboardRow(rowThree));
        keyboardRows.add(createKeyboardRow(rowFour));

        return keyboardRows;
    }
}