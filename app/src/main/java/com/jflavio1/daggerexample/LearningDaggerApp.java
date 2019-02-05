package com.jflavio1.daggerexample;

import android.app.Application;
import com.jflavio1.daggerexample.dagger.prod.DaggerGithubReposComponent;
import com.jflavio1.daggerexample.dagger.prod.GithubReposComponent;
import com.jflavio1.daggerexample.dagger.prod.GithubReposModule;

/**
 * LearningDaggerApp
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class LearningDaggerApp extends Application {

    private GithubReposComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerGithubReposComponent.builder()
                .githubReposModule(new GithubReposModule())
                .build();

    }

    public GithubReposComponent getComponent() {
        return component;
    }
}
