package com.jflavio1.daggerexample.githubrepos.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.BaseActivity;
import com.jflavio1.daggerexample.githubrepos.presenter.GithubReposPresenterImpl;
import com.jflavio1.daggerexample.githubrepos.view.GithubReposView;

public class GithubRepos extends BaseActivity implements GithubReposView {

    // ui
    private Button btn;
    private EditText et;
    private TextView tv;

    // mvp
    private GithubReposPresenterImpl presenter = new GithubReposPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repos);
        this.presenter.injectView(this);
        getComponent().inject(this.presenter);
        initUi();
    }

    private void initUi() {
        btn = findViewById(R.id.githubReposActivity_btn);
        et = findViewById(R.id.githubReposActivity_et);
        tv = findViewById(R.id.githubReposActivity_tv_repos);

        btn.setOnClickListener(v -> presenter.requestGithubReposOfUser(et.getText().toString()));

    }

    @Override
    public void onReposLoaded(String stringList) {
        tv.setText(stringList);
    }

    @Override
    public void onLoadError(Throwable e) {
        Toast.makeText(this, "Error!! " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        this.presenter.onViewDestroyed();
        super.onDestroy();
    }
}
