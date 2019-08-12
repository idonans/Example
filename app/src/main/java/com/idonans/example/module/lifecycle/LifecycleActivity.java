package com.idonans.example.module.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idonans.example.R;

public class LifecycleActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_LIFECYCLE = "fragment#lifecycle";

    public static void start(Context context) {
        Intent starter = new Intent(context, LifecycleActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        LifecycleFragment fragment = (LifecycleFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_LIFECYCLE);
        if (fragment == null) {
            fragment = LifecycleFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, fragment, FRAGMENT_TAG_LIFECYCLE)
                    .commit();
        }
        fragment.setUserVisibleHint(false);
    }

}
