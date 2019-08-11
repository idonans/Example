package com.idonans.example;

import android.app.Application;

import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RxJavaPlugins.setErrorHandler(e -> {
            e.printStackTrace();
            Timber.e(e, "RxJavaPlugins.setErrorHandler");
        });
        Timber.plant(new Timber.DebugTree());
    }

}
