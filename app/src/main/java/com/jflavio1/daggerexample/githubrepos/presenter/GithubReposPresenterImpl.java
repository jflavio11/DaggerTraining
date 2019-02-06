package com.jflavio1.daggerexample.githubrepos.presenter;

import android.util.Log;

import com.jflavio1.daggerexample.domain.model.GithubRepositoryEntity;
import com.jflavio1.daggerexample.domain.repository.GithubReposRepository;
import com.jflavio1.daggerexample.githubrepos.view.GithubReposView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

/**
 * GithubReposPresenterImpl
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class GithubReposPresenterImpl implements GithubReposPresenter {

    @Inject
    GithubReposRepository reposRepository;

    @Inject
    CompositeDisposable compositeDisposable;

    private GithubReposView view;

    @Override
    public void injectView(GithubReposView view) {
        this.view = view;
    }

    @Override
    public void requestGithubReposOfUser(String username) {
        compositeDisposable.add(this.reposRepository.getReposByUsername(username)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<GithubRepositoryEntity> listData = new ArrayList<>();

                    for (GithubRepositoryEntity entity :
                            list) {
                        listData.add(entity);
                    }

                    return listData;
                })
                .subscribe(
                        data -> this.view.onReposLoaded(data),
                        e -> this.view.onLoadError(e)
                )
        );
    }

    @Override
    public void onViewDestroyed() {
        compositeDisposable.dispose();
    }

}
