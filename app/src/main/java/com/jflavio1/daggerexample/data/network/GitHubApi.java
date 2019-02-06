package com.jflavio1.daggerexample.data.network;

import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("/users/{username}/repos")
    Observable<List<GithubRepositoryEntity>> getRepositoriesByUsername (@Path("username") String username);

}
