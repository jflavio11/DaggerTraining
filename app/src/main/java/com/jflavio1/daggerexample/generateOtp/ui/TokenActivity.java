package com.jflavio1.daggerexample.generateOtp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.BaseActivity;
import com.jflavio1.daggerexample.generateOtp.presenter.TokenGeneratorPresenterImpl;
import com.jflavio1.daggerexample.generateOtp.view.TokenGeneratorView;

public class TokenActivity extends BaseActivity implements TokenGeneratorView {

    Button materialButton;
    TextView appCompatTextView;

    private TokenGeneratorPresenterImpl presenter = new TokenGeneratorPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        this.presenter.injectView(this);
        getComponent().inject(this.presenter);
        initUI();
    }

    void initUI(){
        materialButton = findViewById(R.id.btn_generate_token);
        appCompatTextView = findViewById(R.id.txt_token);
        materialButton.setOnClickListener(v -> presenter.generateNewOtpToken());
    }

    @Override
    public void onReposLoaded(String token) {
        appCompatTextView.setText(token);
    }

    @Override
    public void onLoadError(Throwable e) {
        Toast.makeText(this, "Error!! " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.onViewDestroyed();
    }
}
