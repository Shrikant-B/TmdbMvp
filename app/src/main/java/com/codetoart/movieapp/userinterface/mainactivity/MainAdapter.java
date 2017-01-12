package com.codetoart.movieapp.userinterface.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codetoart.movieapp.R;
import com.codetoart.movieapp.injection.ActivityContext;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.userinterface.detailsactivity.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikas on 10-Jan-17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SimpleViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    @Inject
    public MainAdapter(@ActivityContext Context context) {
        this.mContext = context;
        mMovies = new ArrayList<Movie>();
    }

    public void setMovies(List<Movie> moviesList) {
        if (moviesList != null) {
            mMovies.addAll(moviesList);
            notifyDataSetChanged();
        }
    }

    @Override
    public MainAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainAdapter.SimpleViewHolder holder, int position) {
        final Movie movie = mMovies.get(position);
        holder.mMovieTitle.setText(movie.getTitle());
        holder.mMovieReleaseDate.setText(movie.getReleaseDate());

        if(movie.isAdult()){
            holder.mMovieCertificate.setText("(A)");
        }else {
            holder.mMovieCertificate.setText("(U/A)");
        }

        Picasso.with(mContext)
                .load(movie.getPosterPath())
                .into(holder.mMoviePoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("MOVIE", movie);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_poster)
        ImageView mMoviePoster;
        @BindView(R.id.movie_title)
        TextView mMovieTitle;
        @BindView(R.id.movie_release_date)
        TextView mMovieReleaseDate;
        @BindView(R.id.movie_certificate)
        TextView mMovieCertificate;


        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}