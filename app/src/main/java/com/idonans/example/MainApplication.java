package com.idonans.example;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.reactivex.rxjava3.plugins.RxJavaPlugins;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ExampleLog.setLogLevel(Log.VERBOSE);

        RxJavaPlugins.setErrorHandler(e -> {
            e.printStackTrace();
            ExampleLog.e(e, "RxJavaPlugins.setErrorHandler");
        });

        Fresco.initialize(this);
    }

}
