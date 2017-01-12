package com.codetoart.movieapp.database;

import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.model.movie.MovieResponse;
import com.codetoart.movieapp.restapi.TMDBApi;
import com.codetoart.movieapp.utility.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by vikas on 12-Jan-17.
 */

@Singleton
public class DataManager {
    private TMDBApi mTmdbApi;
    private DbHelper mDbHelper;

    @Inject
    public DataManager(TMDBApi tmdbApi, DbHelper dbHelper) {
        this.mTmdbApi = tmdbApi;
        this.mDbHelper = dbHelper;
    }

    public Observable<MovieResponse> getMovieObservable(){
        return mTmdbApi.getUpcomingMovies(Constants.API_KEY)
                .doOnNext(new Action1<MovieResponse>() {
                    @Override
                    public void call(MovieResponse movieResponse) {
                        for (Movie movie: movieResponse.getResults()) {
                            mDbHelper.insertOrReplaceMovie(movie);
                        }
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieResponse>>() {
                    @Override
                    public Observable<? extends MovieResponse> call(Throwable throwable) {
                        return mDbHelper.getLocalMovies(throwable);
                    }
                });
    }
}
