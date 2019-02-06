package com.jflavio1.daggerexample.generateOtp.presenter;

import android.view.View;

import com.jflavio1.daggerexample.generateOtp.view.TokenGeneratorView;

public interface TokenGeneratorPresenter<View extends TokenGeneratorView> {

    void injectView(View view);
    void generateNewOtpToken();
    void onViewDestroyed();

}
