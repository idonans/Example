package io.github.idonans.example.module.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import io.github.idonans.example.databinding.ActivityLifecycleBinding;

public class LifecycleActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_LIFECYCLE = "fragment#lifecycle";

    public static void start(Context context) {
        Intent starter = new Intent(context, LifecycleActivity.class);
        context.startActivity(starter);
    }

    private ActivityLifecycleBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLifecycleBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.pager.setAdapter(new DataAdapter(getSupportFragmentManager()));
    }

    private static class DataAdapter extends FragmentStatePagerAdapter {

        public DataAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return LifecycleFragment.newInstance(String.valueOf(position));
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}
