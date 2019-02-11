package com.jflavio1.daggerexample.keyStoreTest.view;

public interface KeyStoreView {

    void onReposLoaded(String token);

    void onLoadError(Throwable e);

}
