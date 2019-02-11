package com.jflavio1.daggerexample.keyStoreTest.presenter;

import android.content.Context;

import com.jflavio1.daggerexample.domain.repository.KeyStoreRepository;
import com.jflavio1.daggerexample.keyStoreTest.view.KeyStoreView;

import javax.inject.Inject;

public class KeyStorePresenterImpl implements KeyStorePresenter{

    @Inject
    KeyStoreRepository repository;

    private KeyStoreView view;


    @Override
    public void injectView(KeyStoreView view) {
        this.view = view;
    }

    @Override
    public void saveDataOnKeyStore(Context context, String value) {
        try {
            this.view.onReposLoaded(repository.encrypt(context, value));
        } catch (Exception e) {
            this.view.onLoadError(e);
        }
    }

    @Override
    public void getDataFromKeyStore(Context context, String value) {
        try {
            this.view.onReposLoaded(repository.decrypt(context, value));
        } catch (Exception e) {
            this.view.onLoadError(e);
        }
    }

    @Override
    public void onViewDestroyed() {

    }
}
