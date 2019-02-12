package com.jflavio1.daggerexample.keyStoreTest.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.BaseActivity;
import com.jflavio1.daggerexample.keyStoreTest.presenter.KeyStorePresenterImpl;
import com.jflavio1.daggerexample.keyStoreTest.view.KeyStoreView;

public class KeyStoreActivity extends BaseActivity implements KeyStoreView {

    private KeyStorePresenterImpl presenter = new KeyStorePresenterImpl();
    private EditText editTextKeyStore;
    private Button buttonEncrypt;
    private TextView textEncrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_store);
        this.presenter.injectView(this);
        getComponent().inject(this.presenter);
        initUI();

    }


    void initUI() {
        editTextKeyStore = findViewById(R.id.edt_key_store);
        buttonEncrypt = findViewById(R.id.btn_encrypt);
        textEncrypted = findViewById(R.id.encrypt_text);
        buttonEncrypt.setOnClickListener(v->presenter.saveDataOnKeyStore(this, editTextKeyStore.getText().toString()));
    }

    @Override
    public void onReposLoaded(String token) {
        textEncrypted.setText(token);
    }

    @Override
    public void onLoadError(Throwable e) {
        Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();
    }
}
