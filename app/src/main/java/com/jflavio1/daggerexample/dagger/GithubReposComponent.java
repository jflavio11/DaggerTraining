package com.jflavio1.daggerexample.dagger;

import com.jflavio1.daggerexample.generateOtp.presenter.TokenGeneratorPresenterImpl;
import com.jflavio1.daggerexample.githubrepos.presenter.GithubReposPresenterImpl;
import com.jflavio1.daggerexample.keyStoreTest.presenter.KeyStorePresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * GithubReposComponent
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
@Singleton
@Component(modules = {GithubReposModule.class})
public interface GithubReposComponent {
    void inject(GithubReposPresenterImpl presenter);
    void inject(TokenGeneratorPresenterImpl presenter);
    void inject(KeyStorePresenterImpl presenter);
}
