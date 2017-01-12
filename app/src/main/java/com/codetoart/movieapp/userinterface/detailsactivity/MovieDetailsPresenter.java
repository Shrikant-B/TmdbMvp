package com.codetoart.movieapp.userinterface.detailsactivity;

import android.content.Context;
import android.util.Log;

import com.codetoart.movieapp.injection.ActivityContext;
import com.codetoart.movieapp.model.image.Image;
import com.codetoart.movieapp.model.image.ImageResponse;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.restapi.TMDBApi;
import com.codetoart.movieapp.userinterface.baseactivity.BasePresenter;
import com.codetoart.movieapp.utility.Constants;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vikas on 11-Jan-17.
 */

public class MovieDetailsPresenter extends BasePresenter<MovieDetailsMvpView> {

    @Inject
    TMDBApi mTmdbApi;
    private Context mContext;
    private Subscription mSubscription;

    @Inject
    public MovieDetailsPresenter(@ActivityContext Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    public void getConfigAndLoadMovies(String movieId) {
        mSubscription = mTmdbApi.getMovieDetails(movieId, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MovieDetailsActivity", e.toString());
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Log.d("MovieDetailsActivity", "Movie Title: " + movie.getTitle());

                        if (movie.isEmpty()) {
                            getMvpView().showError();
                        } else {
                            getMvpView().showMovieDetails(movie);
                        }
                    }
                });
    }

    public void getConfigAndLoadImages(String movieId) {
        mSubscription = mTmdbApi.getMoviePosters(movieId, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ImageResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MovieDetailsActivity", e.toString());
                    }

                    @Override
                    public void onNext(ImageResponse imageResponse) {
                        Log.d("MovieDetailsActivity", "No of posters: " + imageResponse.getNo());

                        List<Image> images = imageResponse.getPosters();
                        if (images.isEmpty()) {
                            getMvpView().showError();
                        } else {
                            getMvpView().showMovieImages(images);
                        }
                    }
                });
    }
}
