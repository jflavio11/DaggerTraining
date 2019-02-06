package com.jflavio1.daggerexample.githubrepos.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.BaseActivity;
import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;
import com.jflavio1.daggerexample.githubrepos.presenter.GithubReposPresenterImpl;
import com.jflavio1.daggerexample.githubrepos.view.GithubReposView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GithubRepos extends BaseActivity implements GithubReposView {

    // ui
    private Button btn;
    private EditText et;
    private RecyclerView recyclerView;
    private GitHubRepositoriesAdapter adapter;

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

        btn.setOnClickListener(v -> presenter.requestGithubReposOfUser(et.getText().toString()));

    }


    @Override
    public void onReposLoaded(List<GithubRepositoryEntity> data) {
        recyclerView = findViewById(R.id.recycler_view_repositories);
        adapter = new GitHubRepositoriesAdapter(data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GithubRepos.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
