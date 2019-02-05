package com.jflavio1.daggerexample.githubrepos.view;

/**
 * GithubReposView
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public interface GithubReposView {

    void onReposLoaded(String stringList);

    void onLoadError(Throwable e);

}
