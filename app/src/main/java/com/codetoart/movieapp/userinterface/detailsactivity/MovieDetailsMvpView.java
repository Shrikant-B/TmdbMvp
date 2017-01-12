package com.codetoart.movieapp.userinterface.detailsactivity;

import com.codetoart.movieapp.model.image.Image;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.userinterface.baseactivity.MvpView;

import java.util.List;

/**
 * Created by vikas on 11-Jan-17.
 */

public interface MovieDetailsMvpView extends MvpView {
    void showMovieImages(List<Image> images);

    void showError();

    void showMovieDetails(Movie movie);
}
