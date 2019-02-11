package com.jflavio1.daggerexample.domain.repository;

import android.content.Context;

public interface KeyStoreRepository {
    String encrypt(Context context, String value);
    String decrypt(Context context, String value);
}
