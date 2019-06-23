package com.idonans.example;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idonans.lang.WeakAbortSignal;
import com.idonans.lang.thread.Threads;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Threads.postUi(new RedirectToMain(this), 2000L);
    }

    private boolean mPendingRedirect;
    private boolean mStarted;

    @Override
    protected void onStart() {
        super.onStart();
        mStarted = true;

        if (mPendingRedirect) {
            doRedirect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStarted = false;
    }

    private void dispatchRedirect() {
        if (isFinishing()) {
            return;
        }

        if (!mStarted) {
            mPendingRedirect = true;
            return;
        }

        doRedirect();
    }

    private void doRedirect() {
        if (isFinishing()) {
            return;
        }

        MainActivity.start(this);
        finish();
    }

    private static class RedirectToMain extends WeakAbortSignal implements Runnable {

        public RedirectToMain(@Nullable SplashActivity splashActivity) {
            super(splashActivity);
        }

        @Override
        public void run() {
            if (isAbort()) {
                return;
            }

            SplashActivity splashActivity = (SplashActivity) getObject();
            if (splashActivity == null) {
                return;
            }

            splashActivity.dispatchRedirect();
        }

    }

}
