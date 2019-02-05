package com.jflavio1.daggerexample.domain.repository;

import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;
import io.reactivex.Observable;

import java.util.List;

/**
 * GithubReposRepository
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public interface GithubReposRepository {

    Observable<List<GithubRepositoryEntity>> getReposByUsername(String userName);

}
