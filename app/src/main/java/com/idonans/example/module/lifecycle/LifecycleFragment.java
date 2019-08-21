package com.idonans.example.module.lifecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.RealLifecycleFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Lists;
import com.idonans.example.R;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeAdapter;
import com.idonans.uniontype.UnionTypeItemObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class LifecycleFragment extends RealLifecycleFragment {

    public static LifecycleFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString("text", text);
        LifecycleFragment fragment = new LifecycleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String mText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.v("onCreate");
        Bundle arg = getArguments();
        if (arg != null) {
            mText = arg.getString("text");
        }
        new LifecycleObserverImpl(mText, getLifecycle());
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

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    UnionTypeAdapter mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.v("onViewCreated");

        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mAdapter = new UnionTypeAdapter();
        mAdapter.setHost(Host.Factory.create(this, mRecyclerView, mAdapter));
        mAdapter.setUnionTypeMapper(new UnionType());
        mRecyclerView.setAdapter(mAdapter);

        List<UnionTypeItemObject> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new UnionTypeItemObject<>(UnionType.UNION_TYPE_AUTO_TEXT, mText + "#" + i));
        }
        mAdapter.setGroupItems(1, Lists.newArrayList(items));
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
