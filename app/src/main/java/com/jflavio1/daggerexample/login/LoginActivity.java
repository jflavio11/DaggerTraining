package com.jflavio1.daggerexample.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.keyboard.CustomKeyboardView;
import com.jflavio1.daggerexample.core.components.passwordTextInput.PasswordTextInput;
import com.jflavio1.daggerexample.data.network.LocalKeyboardRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public final class LoginActivity extends AppCompatActivity implements LoginView {

    LoginPresenter presenter = new LoginPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter.injectView(this);
        presenter.injectKeyboardRepository(new LocalKeyboardRepositoryImpl());
        presenter.loadServerKeyboardPosition();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onKeyboardPositionsLoaded(List<String> positions) {
        CustomKeyboardView keyboardView = findViewById(R.id.loginActivity_keyboard);
        PasswordTextInput passwordTextInput = findViewById(R.id.loginActivity_pti);
        passwordTextInput.setKeyboardView(keyboardView, (ArrayList) positions);
    }
}
