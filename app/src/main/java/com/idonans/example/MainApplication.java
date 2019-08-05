package com.idonans.example;

import android.app.Application;

import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RxJavaPlugins.setErrorHandler(throwable -> {
        });
        Timber.plant(new Timber.DebugTree());
    }

}
