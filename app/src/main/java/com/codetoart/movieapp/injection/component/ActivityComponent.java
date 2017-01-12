package com.codetoart.movieapp.injection.component;

import com.codetoart.movieapp.userinterface.detailsactivity.MovieDetailsActivity;
import com.codetoart.movieapp.userinterface.mainactivity.MainActivity;
import com.codetoart.movieapp.injection.PerActivity;
import com.codetoart.movieapp.injection.module.ActivityModule;

import dagger.Component;

/**
 * Created by vikas on 10-Jan-17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(MovieDetailsActivity movieDetailsActivity);
}
