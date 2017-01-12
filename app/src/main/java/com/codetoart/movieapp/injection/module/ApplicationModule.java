package com.codetoart.movieapp.injection.module;

import android.app.Application;
import android.content.Context;

import com.codetoart.movieapp.injection.ApplicationContext;
import com.codetoart.movieapp.restapi.TMDBApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikas on 10-Jan-17.
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    /*@Provides
    @Singleton
    public static OkHttpClient provideLogging() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofitInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }*/

    @Provides
    @Singleton
    public TMDBApi provideTmdbApi() {
        return TMDBApi.Factory.provideTmdbApi(mApplication);
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return mApplication;
    }
}
