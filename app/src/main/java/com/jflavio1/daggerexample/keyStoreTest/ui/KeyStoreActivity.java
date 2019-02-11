package com.jflavio1.daggerexample.keyStoreTest.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import timber.log.Timber;

import android.view.View;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.commons.EncryptionUtils;
import com.jflavio1.daggerexample.core.BaseActivity;
import com.jflavio1.daggerexample.domain.repository.KeyStoreRepository;
import com.jflavio1.daggerexample.generateOtp.view.TokenGeneratorView;
import com.jflavio1.daggerexample.keyStoreTest.presenter.KeyStorePresenter;
import com.jflavio1.daggerexample.keyStoreTest.presenter.KeyStorePresenterImpl;
import com.jflavio1.daggerexample.keyStoreTest.view.KeyStoreView;

public class KeyStoreActivity extends BaseActivity implements KeyStoreView {

    private KeyStorePresenterImpl presenter = new KeyStorePresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_store);
        this.presenter.injectView(this);
        getComponent().inject(this.presenter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String value = "Avantica Technologies - Caja Piura mobile app";

        String encryptedValue = EncryptionUtils.encrypt(this, value);
        Timber.d(" Encrypted Value :" + encryptedValue);

        String decryptedValue = EncryptionUtils.decrypt(this, encryptedValue);
        Timber.d(" Decrypted Value :" + decryptedValue);

    }

    @Override
    public void onReposLoaded(String token) {

    }

    @Override
    public void onLoadError(Throwable e) {

    }
}
