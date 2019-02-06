package com.jflavio1.daggerexample.generateOtp.presenter;

import com.jflavio1.daggerexample.domain.repository.OTPTokenRepository;
import com.jflavio1.daggerexample.generateOtp.view.TokenGeneratorView;

import javax.inject.Inject;

public class TokenGeneratorPresenterImpl implements TokenGeneratorPresenter {

    @Inject
    OTPTokenRepository otpTokenRepository;

    private TokenGeneratorView view;


    @Override
    public void injectView(TokenGeneratorView view) {
        this.view = view;
    }

    @Override
    public void generateNewOtpToken() {
        try {
            this.view.onReposLoaded(otpTokenRepository.getSixDigitsToken());
        }catch (Exception e){
            this.view.onLoadError(e);
        }
    }

    @Override
    public void onViewDestroyed() {

    }
}
