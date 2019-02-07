package com.jflavio1.daggerexample.login;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.keyboard.CustomKeyboardView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private CustomKeyboardView keyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editText = findViewById(R.id.loginActivity_et);

        keyboardView = findViewById(R.id.loginActivity_keyboard);

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

        CustomKeyboardView.KeyboardType type = CustomKeyboardView.KeyboardType.BANK_PASSWORD;
        type.setServerKeyValues(serverKeyValues);

        keyboardView.registerEditText(type, editText);

    }

    @Override
    public void onBackPressed() {
        if (keyboardView.isExpanded()) {
            keyboardView.translateLayout();
        } else {
            super.onBackPressed();
        }
    }

}
