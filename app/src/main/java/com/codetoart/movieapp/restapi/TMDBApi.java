package com.codetoart.movieapp.restapi;

import android.content.Context;
import android.support.compat.BuildConfig;

import com.codetoart.movieapp.model.image.ImageResponse;
import com.codetoart.movieapp.model.movie.Movie;
import com.codetoart.movieapp.model.movie.MovieResponse;
import com.codetoart.movieapp.utility.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vikas on 10-Jan-17.
 */

public interface TMDBApi {

    @GET("movie/upcoming")
    Observable<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<Movie> getMovieDetails(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("movie/{id}/images")
    Observable<ImageResponse> getMoviePosters(@Path("id") String id, @Query("api_key") String apiKey);

    class Factory {
        public static TMDBApi provideTmdbApi(Context context) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit.create(TMDBApi.class);
        }
    }
}
