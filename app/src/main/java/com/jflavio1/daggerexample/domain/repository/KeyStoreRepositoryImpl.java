package com.jflavio1.daggerexample.domain.repository;

import android.content.Context;

import com.jflavio1.daggerexample.commons.EncryptionUtils;

public class KeyStoreRepositoryImpl implements KeyStoreRepository{

    @Override
    public String encrypt(Context context, String value) {
        return EncryptionUtils.encrypt(context,value);
    }

    @Override
    public String decrypt(Context context, String value) {
        return EncryptionUtils.decrypt(context, value);
    }
}
