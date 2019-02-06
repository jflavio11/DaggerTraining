package com.jflavio1.daggerexample.generateOtp.view;

public interface TokenGeneratorView {

    void onReposLoaded(String token);

    void onLoadError(Throwable e);

}
