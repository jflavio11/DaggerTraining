package com.jflavio1.daggerexample.core;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.LearningDaggerApp;
import com.jflavio1.daggerexample.dagger.GithubReposComponent;

/**
 * BaseActivity
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected GithubReposComponent getComponent() {
        return ((LearningDaggerApp) getApplication()).getComponent();
    }

}
