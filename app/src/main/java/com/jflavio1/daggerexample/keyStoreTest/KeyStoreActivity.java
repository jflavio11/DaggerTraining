package com.jflavio1.daggerexample.keyStoreTest;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import timber.log.Timber;

import android.view.View;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.commons.EncryptionUtils;

public class KeyStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_store);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Timber.plant(new Timber.DebugTree());

        String value = "Avantica Technologies - Caja Piura mobile app";

        String encryptedValue = EncryptionUtils.encrypt(this, value);
        Timber.d(" Encrypted Value :" + encryptedValue);

        String decryptedValue = EncryptionUtils.decrypt(this, encryptedValue);
        Timber.d(" Decrypted Value :" + decryptedValue);

    }

}
