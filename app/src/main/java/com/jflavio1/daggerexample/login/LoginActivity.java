package com.jflavio1.daggerexample.login;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.keyboard.CustomKeyboardView;

public class LoginActivity extends AppCompatActivity {

    private CustomKeyboardView keyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editText = findViewById(R.id.loginActivity_et);
        EditText editText2 = findViewById(R.id.loginActivity_et2);

        keyboardView = findViewById(R.id.loginActivity_keyboard);

        keyboardView.registerEditText(CustomKeyboardView.KeyboardType.NUMBER, editText);
        //keyboardView.registerEditText(CustomKeyboardView.KeyboardType.NUMBER, editText2);

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
