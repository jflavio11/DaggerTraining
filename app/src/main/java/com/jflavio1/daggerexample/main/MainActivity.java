package com.jflavio1.daggerexample.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.dialogssample.DialogsSampleActivity;
import com.jflavio1.daggerexample.generateOtp.ui.TokenActivity;
import com.jflavio1.daggerexample.githubrepos.ui.GithubRepos;
import com.jflavio1.daggerexample.keyStoreTest.KeyStoreActivity;
import com.jflavio1.daggerexample.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGithubRepos, btnLoginKeyboard, btnToken, btnKeyStore, btnDialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGithubRepos = findViewById(R.id.btn_github_repos);
        btnLoginKeyboard = findViewById(R.id.btn_login_keyboard);
        btnToken = findViewById(R.id.btn_token);
        btnKeyStore = findViewById(R.id.btn_key_store);
        btnDialogs = findViewById(R.id.mainActivity_btn_dialogs);

        btnGithubRepos.setOnClickListener(v -> startActivity(new Intent(this, GithubRepos.class)));

        btnLoginKeyboard.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        btnToken.setOnClickListener(v -> startActivity(new Intent(this, TokenActivity.class)));

        btnKeyStore.setOnClickListener(v -> startActivity(new Intent(this, KeyStoreActivity.class)));

        btnDialogs.setOnClickListener(v -> startActivity(new Intent(this, DialogsSampleActivity.class)));

    }

}
