package com.codetoart.movieapp.userinterface.baseactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codetoart.movieapp.UpcomingMovieApplication;
import com.codetoart.movieapp.injection.component.ActivityComponent;
import com.codetoart.movieapp.injection.component.DaggerActivityComponent;
import com.codetoart.movieapp.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(UpcomingMovieApplication.get(this).getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
