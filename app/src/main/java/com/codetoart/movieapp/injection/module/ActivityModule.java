package com.codetoart.movieapp.injection.module;

import android.app.Activity;
import android.content.Context;

import com.codetoart.movieapp.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikas on 10-Jan-17.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    public Context providesContext() {
        return mActivity;
    }
}
