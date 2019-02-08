package com.jflavio1.daggerexample.generateOtp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.BaseActivity;
import com.jflavio1.daggerexample.generateOtp.presenter.TokenGeneratorPresenterImpl;
import com.jflavio1.daggerexample.generateOtp.view.TokenGeneratorView;

public class TokenActivity extends BaseActivity implements TokenGeneratorView {

    TextView appCompatTextView;
    CountDownTimer countDownTimer;

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
        appCompatTextView = findViewById(R.id.txt_token);
        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
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
        countDownTimer.cancel();
    }
}
