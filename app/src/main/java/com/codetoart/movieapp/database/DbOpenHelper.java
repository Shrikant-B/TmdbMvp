package com.codetoart.movieapp.database;

import android.content.Context;

import com.codetoart.movieapp.injection.ApplicationContext;
import com.codetoart.movieapp.model.movie.DaoMaster;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vikas on 12-Jan-17.
 */

@Singleton
public class DbOpenHelper extends DaoMaster.DevOpenHelper {

    private static final String DATABASE_NAME = "movieApp.db";

    @Inject
    public DbOpenHelper(@ApplicationContext Context context) {
        super(context,DATABASE_NAME);
    }
}
