package com.idonans.example.module.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import timber.log.Timber;

public class LifecycleObserverImpl implements LifecycleObserver {

    private String mPrefix;
    private final Lifecycle mLifecycle;

    public LifecycleObserverImpl(String prefix, Lifecycle lifecycle) {
        mPrefix = prefix;
        mLifecycle = lifecycle;
        mLifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Timber.v("%s onCreate", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Timber.v("%s onStart", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Timber.v("%s onResume", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Timber.v("%s onPause", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Timber.v("%s onStop", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Timber.v("%s onDestroy", mPrefix);
        mLifecycle.removeObserver(this);
    }

}
