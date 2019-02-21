package com.jflavio1.daggerexample;

import android.app.Application;


import com.jflavio1.daggerexample.dagger.DaggerGithubReposComponent;
import com.jflavio1.daggerexample.dagger.GithubReposComponent;
import com.jflavio1.daggerexample.dagger.GithubReposModule;

import timber.log.Timber;

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

        Timber.plant(new Timber.DebugTree());

    }

    public GithubReposComponent getComponent() {
        return component;
    }
}
