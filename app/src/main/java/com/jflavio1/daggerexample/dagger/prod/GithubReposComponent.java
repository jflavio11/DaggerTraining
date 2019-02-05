package com.jflavio1.daggerexample.dagger.prod;

import com.jflavio1.daggerexample.githubrepos.presenter.GithubReposPresenterImpl;
import dagger.Component;

/**
 * GithubReposComponent
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
@Component(modules = {GithubReposModule.class})
public interface GithubReposComponent {
    void inject(GithubReposPresenterImpl presenter);
}
