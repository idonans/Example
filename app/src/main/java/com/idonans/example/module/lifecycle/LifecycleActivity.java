package com.idonans.example.module.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.idonans.example.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifecycleActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_LIFECYCLE = "fragment#lifecycle";

    public static void start(Context context) {
        Intent starter = new Intent(context, LifecycleActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.pager)
    ViewPager mPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        ButterKnife.bind(this);

        mPager.setAdapter(new DataAdapter(getSupportFragmentManager()));
    }

    private class DataAdapter extends FragmentStatePagerAdapter {

        public DataAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LifecycleFragment.newInstance(String.valueOf(position));
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

}
