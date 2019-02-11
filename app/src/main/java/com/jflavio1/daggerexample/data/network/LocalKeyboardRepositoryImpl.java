package com.jflavio1.daggerexample.data.network;

import com.jflavio1.daggerexample.domain.repository.KeyboardRepository;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;

/**
 * LocalKeyboardRepositoryImpl
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public class LocalKeyboardRepositoryImpl implements KeyboardRepository {
    @Override
    public Single<List<String>> loadServerKeyboard() {
        return Single.create(emitter -> {
            ArrayList<String> serverKeyValues = new ArrayList<>();
            serverKeyValues.add("1");
            serverKeyValues.add("3");
            serverKeyValues.add("8");
            serverKeyValues.add("2");
            serverKeyValues.add("6");
            serverKeyValues.add("0");
            serverKeyValues.add("4");
            serverKeyValues.add("9");
            serverKeyValues.add("7");
            serverKeyValues.add("5");

            emitter.onSuccess(serverKeyValues);
        });
    }
}
