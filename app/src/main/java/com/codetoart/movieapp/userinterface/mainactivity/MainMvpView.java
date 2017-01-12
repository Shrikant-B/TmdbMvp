package com.codetoart.movieapp.userinterface.mainactivity;

import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.userinterface.baseactivity.MvpView;

import java.util.List;

/**
 * Created by vikas on 10-Jan-17.
 */

public interface MainMvpView extends MvpView {
    void showMovies(List<Movie> movies);

    void showMovieProgress(boolean show);

    void showErrorMessage();
}
