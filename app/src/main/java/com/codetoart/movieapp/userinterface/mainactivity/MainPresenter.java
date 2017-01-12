package com.codetoart.movieapp.userinterface.mainactivity;

import android.content.Context;
import android.util.Log;

import com.codetoart.movieapp.database.DataManager;
import com.codetoart.movieapp.injection.ActivityContext;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.model.movie.MovieResponse;
import com.codetoart.movieapp.restapi.TMDBApi;
import com.codetoart.movieapp.userinterface.baseactivity.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vikas on 10-Jan-17.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {

    /*@Inject
    TMDBApi mTmdbApi;*/
    //DbHelper mDbHelper;

    DataManager mDataManager;

    private Context mContext;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(@ActivityContext Context context, DataManager dataManager) {       //DbHelper dbHelper) {
        this.mContext = context;
        this.mDataManager = dataManager;

        //this.mDbHelper = dbHelper;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    public void getConfigAndLoadMovies() {
        mSubscription = mDataManager.getMovieObservable()

                //mTmdbApi.getUpcomingMovies(Constants.API_KEY)
                /*.doOnNext(new Action1<MovieResponse>() {
                    @Override
                    public void call(MovieResponse movieResponse) {
                        for (Movie movie: movieResponse.getResults()) {
                            //mDbHelper.insertOrReplaceMovie(movie);
                        }
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieResponse>>() {
                    @Override
                    public Observable<? extends MovieResponse> call(Throwable throwable) {
                        return mDbHelper.getLocalMovies(throwable);
                    }
                })*/

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", e.toString());
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        Log.d("MainActivity", "No of movies: " + movieResponse.getTotalResults());

                        getMvpView().showMovieProgress(false);
                        List<Movie> movies = movieResponse.getResults();
                        if (movies.isEmpty()) {
                            getMvpView().showErrorMessage();
                        } else {
                            getMvpView().showMovies(movies);
                        }
                    }
                });
    }
}
