package com.jflavio1.daggerexample.dagger.prod;

import com.jflavio1.daggerexample.domain.repository.GithubReposRepositoryImpl;
import com.jflavio1.daggerexample.domain.repository.GithubReposRepository;
import com.jflavio1.daggerexample.domain.repository.OTPTokenRepository;
import com.jflavio1.daggerexample.domain.repository.OTPTokenRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * GithubReposModule
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
@Module
public class GithubReposModule {

    @Provides
    public GithubReposRepository providesGithubReposRepository() {
        return new GithubReposRepositoryImpl();
    }

    @Provides
    public OTPTokenRepository providesOTPTokenRepository() {
        return new OTPTokenRepositoryImpl();
    }

    @Provides
    public CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

}
