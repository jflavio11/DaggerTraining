package com.jflavio1.daggerexample.domain.repository;

import io.reactivex.Single;

import java.util.List;

/**
 * KeyboardRepository
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public interface KeyboardRepository {

    Single<List<String>> loadServerKeyboard();

}
