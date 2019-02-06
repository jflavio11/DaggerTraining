package com.jflavio1.daggerexample.login;

import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.VKeyboard;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editText = findViewById(R.id.loginActivity_et);
        VKeyboard vKeyboard = findViewById(R.id.loginActivity_keyboard);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);


        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        vKeyboard.setInputConnection(ic);

    }
}
