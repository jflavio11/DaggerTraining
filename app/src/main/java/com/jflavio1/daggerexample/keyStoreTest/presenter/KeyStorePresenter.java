package com.jflavio1.daggerexample.keyStoreTest.presenter;

import android.content.Context;
import android.view.View;

import com.jflavio1.daggerexample.keyStoreTest.view.KeyStoreView;

public interface KeyStorePresenter <View extends KeyStoreView>{

    void injectView(View view);
    void saveDataOnKeyStore(Context context, String value);
    void getDataFromKeyStore(Context context, String value);
    void onViewDestroyed();

}
