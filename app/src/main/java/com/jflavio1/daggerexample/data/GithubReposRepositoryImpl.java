package com.jflavio1.daggerexample.data;

import android.accounts.NetworkErrorException;
import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;
import com.jflavio1.daggerexample.domain.repository.GithubReposRepository;
import io.reactivex.Observable;

import java.util.List;

/**
 * GithubReposRepositoryImpl
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class GithubReposRepositoryImpl implements GithubReposRepository {
    @Override
    public Observable<List<GithubRepositoryEntity>> getReposByUsername(String userName) {
        return Observable.error(new NetworkErrorException());
    }
}
