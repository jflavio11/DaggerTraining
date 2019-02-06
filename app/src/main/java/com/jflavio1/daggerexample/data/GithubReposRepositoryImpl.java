package com.jflavio1.daggerexample.data;

import android.util.Log;

import com.jflavio1.daggerexample.data.network.GitHubApi;
import com.jflavio1.daggerexample.data.network.RetrofitInstance;
import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;
import com.jflavio1.daggerexample.domain.repository.GithubReposRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * GithubReposRepositoryImpl
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class GithubReposRepositoryImpl implements GithubReposRepository {
    @Override
    public Observable<List<GithubRepositoryEntity>> getReposByUsername(String userName) {
        GitHubApi api = RetrofitInstance.getRetrofitInstance().create(GitHubApi.class);
        Log.i("API CALL", api.getRepositoriesByUsername(userName).toString());
        return api.getRepositoriesByUsername(userName);
    }
}
