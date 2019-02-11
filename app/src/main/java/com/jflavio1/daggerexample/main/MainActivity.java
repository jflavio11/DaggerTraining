package com.jflavio1.daggerexample.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.generateOtp.ui.TokenActivity;
import com.jflavio1.daggerexample.githubrepos.ui.GithubRepos;
import com.jflavio1.daggerexample.keyStoreTest.KeyStoreActivity;
import com.jflavio1.daggerexample.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGithubRepos, btnLoginKeyboard, btnToken, btnKeyStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGithubRepos = findViewById(R.id.btn_github_repos);
        btnLoginKeyboard = findViewById(R.id.btn_login_keyboard);
        btnToken = findViewById(R.id.btn_token);
        btnKeyStore = findViewById(R.id.btn_key_store);

        btnGithubRepos.setOnClickListener(v -> startActivity(new Intent(this, GithubRepos.class)));

        btnLoginKeyboard.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        btnToken.setOnClickListener(v -> startActivity(new Intent(this, TokenActivity.class)));

        btnKeyStore.setOnClickListener(v -> startActivity(new Intent(this, KeyStoreActivity.class)));

    }

}
