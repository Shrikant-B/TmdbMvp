package com.codetoart.movieapp.userinterface.baseactivity;

/**
 * Created by vikas on 10-Jan-17.
 */

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
