package com.codetoart.movieapp.userinterface.detailsactivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codetoart.movieapp.R;
import com.codetoart.movieapp.model.image.Image;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.userinterface.baseactivity.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsMvpView {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.circleIndicator)
    CircleIndicator mCircleIndicator;
    @BindView(R.id.movieTitle)
    TextView mMovieTitle;
    @BindView(R.id.movieOverview)
    TextView mMovieOverview;
    @BindView(R.id.movieRating)
    RatingBar mMovieRating;

    @Inject
    MovieDetailsPresenter mMovieDetailsPresenter;
    @Inject
    MovieDetailsAdapter mMovieDetailsAdapter;

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mMovieDetailsPresenter.attachView(this);

        mViewPager.setAdapter(mMovieDetailsAdapter);
        mCircleIndicator.setViewPager(mViewPager);
        mMovieDetailsAdapter.registerDataSetObserver(mCircleIndicator.getDataSetObserver());

        mMovie = getIntent().getParcelableExtra("MOVIE");
        //showMovieDetails();
        loadMovieDetails();
    }

    private void loadMovieDetails() {
        mMovieDetailsPresenter.getConfigAndLoadMovies(mMovie.getId());
        mMovieDetailsPresenter.getConfigAndLoadImages(mMovie.getId());
    }

    /*private void showMovieDetails() {
        mMovieTitle.setText(mMovie.getTitle());
        mMovieOverview.setText(mMovie.getOverview());
        mMovieRating.setRating(mMovie.getVoteAverage() / 2.0f);
    }*/

    @Override
    public void showMovieImages(List<Image> images) {
        mMovieDetailsAdapter.setImages(images);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Failed to load movie details", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMovieDetails(Movie movie) {
        mMovieTitle.setText(mMovie.getTitle());
        mMovieOverview.setText(mMovie.getOverview());
        mMovieRating.setRating(mMovie.getVoteAverage() / 2.0f);
    }
}
