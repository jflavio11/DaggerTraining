package com.jflavio1.daggerexample.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.keyboard.CustomKeyboardView;
import com.jflavio1.daggerexample.core.components.keyboard.KeyboardListener;
import com.jflavio1.daggerexample.core.components.keyboard.KeyboardType;
import com.jflavio1.daggerexample.core.components.keyboard.controller.KeyboardController;

import java.util.ArrayList;

public final class LoginActivity extends AppCompatActivity {

    private CustomKeyboardView keyboardView;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;

    private EditText[] passFields = new EditText[6];

    private int currentEditTextPosition = 0;

    private ArrayList<Character> positionsClicked = new ArrayList<>();
    private View.OnClickListener clickListener = v -> {
        if (currentEditTextPosition == 5) {
            for (EditText passField : passFields) {
                passField.getText().clear();
            }
            currentEditTextPosition = 0;
            passFields[currentEditTextPosition].requestFocus();
        } else if (currentEditTextPosition == 0) {
            passFields[currentEditTextPosition].requestFocus();
            keyboardView.translateLayout();
        } else if (keyboardView.theViewIsExpanded() && currentEditTextPosition < 5) {
            passFields[currentEditTextPosition + 1].requestFocus();
        } else {
            keyboardView.translateLayout();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO: edittexts could be a composition of unique custom view
        // for checking when click onto the custom view (the viewgroup)
        // is done and then start typing (like BCP app)
        editText = findViewById(R.id.loginActivity_et_pass1);
        editText2 = findViewById(R.id.loginActivity_et_pass2);
        editText3 = findViewById(R.id.loginActivity_et_pass3);
        editText4 = findViewById(R.id.loginActivity_et_pass4);
        editText5 = findViewById(R.id.loginActivity_et_pass5);
        editText6 = findViewById(R.id.loginActivity_et_pass6);

        passFields[0] = editText;
        passFields[1] = editText2;
        passFields[2] = editText3;
        passFields[3] = editText4;
        passFields[4] = editText5;
        passFields[5] = editText6;

        keyboardView = findViewById(R.id.loginActivity_keyboard);

        // TODO get key values from server/file
        ArrayList<String> serverKeyValues = new ArrayList<>();
        serverKeyValues.add("1");
        serverKeyValues.add("3");
        serverKeyValues.add("8");
        serverKeyValues.add("2");
        serverKeyValues.add("6");
        serverKeyValues.add("0");
        serverKeyValues.add("4");
        serverKeyValues.add("9");
        serverKeyValues.add("7");
        serverKeyValues.add("5");

        KeyboardType type = KeyboardType.BANK_PASSWORD;
        type.setServerKeyValues(serverKeyValues);

        // TODO this part of code can reside in a keyboardView subclass
        keyboardView.setListener(new KeyboardListener() {
            @Override
            public void characterClicked(char c) {
                if (currentEditTextPosition < 5) {
                    currentEditTextPosition++;
                    passFields[currentEditTextPosition].requestFocus();
                } else {
                    keyboardView.translateLayout();
                }
                positionsClicked.add(c);
            }

            @Override
            public void specialKeyClicked(KeyboardController.SpecialKey key) {
                if (key == KeyboardController.SpecialKey.DELETE_ALL) {
                    for (EditText passField : passFields) {
                        passField.clearFocus();
                        passField.getText().clear();
                    }
                    currentEditTextPosition = 0;
                }
            }
        });

        for (EditText passField : passFields) {
            keyboardView.registerEditText(type, passField);
            passField.setOnClickListener(clickListener);
        }
    }

    @Override
    public void onBackPressed() {

    }

}
