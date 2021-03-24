package com.idonans.example.module.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.idonans.example.ExampleLog;

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
        ExampleLog.v("%s onCreate", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        ExampleLog.v("%s onStart", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        ExampleLog.v("%s onResume", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        ExampleLog.v("%s onPause", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        ExampleLog.v("%s onStop", mPrefix);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        ExampleLog.v("%s onDestroy", mPrefix);
        mLifecycle.removeObserver(this);
    }

}
