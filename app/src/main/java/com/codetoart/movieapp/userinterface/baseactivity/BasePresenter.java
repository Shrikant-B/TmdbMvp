package com.codetoart.movieapp.userinterface.baseactivity;

/**
 * Created by vikas on 10-Jan-17.
 */

public class BasePresenter<V extends MvpView> implements Presenter<V> {

    private V mvpView;

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        return mvpView;
    }

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }
}
