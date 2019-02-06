package com.jflavio1.daggerexample.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import com.jflavio1.daggerexample.R;

/**
 * VKeyboard
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 6/2/17
 */
public class VKeyboard extends LinearLayout implements View.OnClickListener {

    private AppCompatButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDelete;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    private ClickedButtonsListener clickedButtonsListener;

    public VKeyboard(Context context) {
        super(context);
    }

    public VKeyboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VKeyboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VKeyboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.layout_keyboard, this, true);

        // getting views
        btn0 = findViewById(R.id.keyboard_btn_0);
        btn1 = findViewById(R.id.keyboard_btn_1);
        btn2 = findViewById(R.id.keyboard_btn_2);
        btn3 = findViewById(R.id.keyboard_btn_3);
        btn4 = findViewById(R.id.keyboard_btn_4);
        btn5 = findViewById(R.id.keyboard_btn_5);
        btn6 = findViewById(R.id.keyboard_btn_6);
        btn7 = findViewById(R.id.keyboard_btn_7);
        btn8 = findViewById(R.id.keyboard_btn_8);
        btn9 = findViewById(R.id.keyboard_btn_9);
        btnDelete = findViewById(R.id.keyboard_btn_delete);

        // setting click listeners
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

    }

    public void setKeyboardValues(String[] serverValues) {

        if (serverValues.length < 10) {
            return;
        }

        keyValues.put(R.id.keyboard_btn_0, serverValues[0]);
        keyValues.put(R.id.keyboard_btn_1, serverValues[1]);
        keyValues.put(R.id.keyboard_btn_2, serverValues[2]);
        keyValues.put(R.id.keyboard_btn_3, serverValues[3]);
        keyValues.put(R.id.keyboard_btn_4, serverValues[4]);
        keyValues.put(R.id.keyboard_btn_5, serverValues[5]);
        keyValues.put(R.id.keyboard_btn_6, serverValues[6]);
        keyValues.put(R.id.keyboard_btn_7, serverValues[7]);
        keyValues.put(R.id.keyboard_btn_8, serverValues[8]);
        keyValues.put(R.id.keyboard_btn_9, serverValues[9]);

    }

    @Override
    public void onClick(View v) {
        if (inputConnection == null) {
            return;
        }

        if (v.getId() == R.id.keyboard_btn_delete) {
            CharSequence selectedTxt = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedTxt)) {
                // removes empty space
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                // removes the character
                inputConnection.commitText("", 1);
            }

            if (clickedButtonsListener != null) {
                clickedButtonsListener.onLastValueRemoved();
            }

        } else {
            String value = keyValues.get(v.getId());
            if (clickedButtonsListener != null) {
                clickedButtonsListener.onValueClicked(keyValues.indexOfKey(v.getId()));
            }
            inputConnection.commitText(value, 1);
        }

    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }

    public void setClickedButtonsListener(ClickedButtonsListener clickedButtonsListener) {
        this.clickedButtonsListener = clickedButtonsListener;
    }

    interface ClickedButtonsListener {
        void onValueClicked(int position);

        void onLastValueRemoved();
    }

}
