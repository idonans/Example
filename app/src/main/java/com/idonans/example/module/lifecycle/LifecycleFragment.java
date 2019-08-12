package com.idonans.example.module.lifecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.RealLifecycleFragment;

import com.idonans.example.R;

import timber.log.Timber;

public class LifecycleFragment extends RealLifecycleFragment {

    public static LifecycleFragment newInstance() {
        Bundle args = new Bundle();
        LifecycleFragment fragment = new LifecycleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.v("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.v("onCreateView");
        if (container == null) {
            container = new FrameLayout(inflater.getContext());
        }

        return inflater.inflate(R.layout.fragment_lifecycle, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.v("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.v("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.v("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.v("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.v("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.v("onDestroy");
    }

}
