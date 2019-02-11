package com.jflavio1.daggerexample.login;

import com.jflavio1.daggerexample.domain.repository.KeyboardRepository;

/**
 * LoginPresenter
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public interface LoginPresenter {

    void injectView(LoginView view);

    void injectKeyboardRepository(KeyboardRepository repository);

    void loadServerKeyboardPosition();

    void onDestroyView();

}
