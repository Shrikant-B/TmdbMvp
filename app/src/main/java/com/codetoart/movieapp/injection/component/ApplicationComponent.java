package com.codetoart.movieapp.injection.component;

import android.app.Application;
import android.content.Context;

import com.codetoart.movieapp.database.DataManager;
import com.codetoart.movieapp.database.DbHelper;
import com.codetoart.movieapp.injection.ApplicationContext;
import com.codetoart.movieapp.injection.module.ApplicationModule;
import com.codetoart.movieapp.UpcomingMovieApplication;
import com.codetoart.movieapp.restapi.TMDBApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vikas on 10-Jan-17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(UpcomingMovieApplication upcomingMovieApplication);

    @ApplicationContext
    Context context();
    Application application();
    TMDBApi tmdbApi();
    DbHelper dbHelper();
    DataManager dataManager();
}
