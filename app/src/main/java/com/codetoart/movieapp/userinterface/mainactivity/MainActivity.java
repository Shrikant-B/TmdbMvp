package com.codetoart.movieapp.userinterface.mainactivity;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codetoart.movieapp.R;
import com.codetoart.movieapp.model.movie.DaoSession;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.userinterface.baseactivity.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mMainPresenter.attachView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMainAdapter);

        loadMovies();
    }

    private void loadMovies() {
        mMainPresenter.getConfigAndLoadMovies();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mMainAdapter.setMovies(movies);
    }

    @Override
    public void showMovieProgress(boolean show) {
        if (show && mMainAdapter.getItemCount() == 0) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Failed to load movies", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }
}
