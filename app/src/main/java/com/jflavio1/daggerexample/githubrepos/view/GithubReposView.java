package com.jflavio1.daggerexample.githubrepos.view;

import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;

import java.util.List;

/**
 * GithubReposView
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public interface GithubReposView {

    void onReposLoaded(List<GithubRepositoryEntity> data);

    void onLoadError(Throwable e);

}
