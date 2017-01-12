package com.codetoart.movieapp;

import android.app.Application;
import android.content.Context;

import com.codetoart.movieapp.injection.component.ApplicationComponent;
import com.codetoart.movieapp.injection.component.DaggerApplicationComponent;
import com.codetoart.movieapp.injection.module.ApplicationModule;

/**
 * Created by vikas on 10-Jan-17.
 */

public class UpcomingMovieApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    public static UpcomingMovieApplication get(Context context) {
        return (UpcomingMovieApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.mApplicationComponent = applicationComponent;
    }
}
