package com.jflavio1.daggerexample.login;

import com.jflavio1.daggerexample.domain.repository.KeyboardRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * LoginPresenterImpl
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public class LoginPresenterImpl implements LoginPresenter {

    LoginView view;
    KeyboardRepository repository;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void injectView(LoginView view) {
        this.view = view;
    }

    @Override
    public void injectKeyboardRepository(KeyboardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadServerKeyboardPosition() {
        Disposable disposable = this.repository.loadServerKeyboard()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(strings -> {
                    view.onKeyboardPositionsLoaded(strings);
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroyView() {
        compositeDisposable.clear();
    }

}
