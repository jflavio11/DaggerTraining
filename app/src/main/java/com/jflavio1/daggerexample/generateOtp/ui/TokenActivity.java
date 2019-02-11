package com.jflavio1.daggerexample.generateOtp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.BaseActivity;
import com.jflavio1.daggerexample.generateOtp.presenter.TokenGeneratorPresenterImpl;
import com.jflavio1.daggerexample.generateOtp.view.TokenGeneratorView;

public class TokenActivity extends BaseActivity implements TokenGeneratorView {

    TextView tokenTextView;
    TextView secondsRemainingTextView;
    CountDownTimer countDownTimer;
    ProgressBar progressBar;

    private TokenGeneratorPresenterImpl presenter = new TokenGeneratorPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        this.presenter.injectView(this);
        getComponent().inject(this.presenter);

        initUI();
        initCountDownTimer();
    }

    void initUI() {
        tokenTextView = findViewById(R.id.txt_token);
        progressBar = findViewById(R.id.progress_circular_token);
        secondsRemainingTextView = findViewById(R.id.txt_token_time);
    }

    void initCountDownTimer() {
        this.presenter.generateNewOtpToken();
        progressBar.setProgress(60);
        countDownTimer = new CountDownTimer(60000,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(Math.round(millisUntilFinished * 0.001f));
                String seconds = String.valueOf(Math.round(millisUntilFinished * 0.001f));
                secondsRemainingTextView.setText("Expira en " + seconds + " seg");
            }
            @Override
            public void onFinish() {
                initCountDownTimer();
            }
        }.start();
    }

    @Override
    public void onReposLoaded(String token) {
        tokenTextView.setText(token);
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
