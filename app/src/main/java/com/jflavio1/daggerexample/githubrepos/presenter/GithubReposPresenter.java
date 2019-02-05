package com.jflavio1.daggerexample.githubrepos.presenter;

import com.jflavio1.daggerexample.githubrepos.view.GithubReposView;

/**
 * GithubReposPresenter
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public interface GithubReposPresenter<View extends GithubReposView> {

    void injectView(View view);

    void requestGithubReposOfUser(String username);

    void onViewDestroyed();

}
