package com.codetoart.movieapp.database;

import android.content.Context;

import com.codetoart.movieapp.injection.ApplicationContext;
import com.codetoart.movieapp.model.image.Image;
import com.codetoart.movieapp.model.movie.DaoMaster;
import com.codetoart.movieapp.model.movie.DaoSession;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.model.movie.MovieResponse;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.rx.RxDao;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by vikas on 12-Jan-17.
 */

@Singleton
public class DbHelper {

    private DaoSession mDaoSession;

    @Inject
    public DbHelper(@ApplicationContext Context context) {
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        Database database = dbOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }

    /*public DaoSession getDaoSession() {
        return mDaoSession;
    }*/

    /*public RxDao<Movie, String> getMovieDao() {
        return mDaoSession.getMovieDao().rx();
    }*/

    public void insertOrReplaceMovie(Movie movie) {
        mDaoSession.insertOrReplace(movie);
    }

    public Observable<MovieResponse> getLocalMovies(final Throwable error) {
        ArrayList<Movie> movies = (ArrayList<Movie>) mDaoSession.getMovieDao().loadAll();
        final MovieResponse movieResponse = new MovieResponse();
        movieResponse.setResults(movies);

        Observable<MovieResponse> movieResponseObservable = Observable.create(new Observable.OnSubscribe<MovieResponse>() {
            @Override
            public void call(Subscriber<? super MovieResponse> subscriber) {
                if (!movieResponse.getResults().isEmpty()) {
                    subscriber.onNext(movieResponse);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(error);
                }
            }
        });

        return movieResponseObservable;
    }
}
