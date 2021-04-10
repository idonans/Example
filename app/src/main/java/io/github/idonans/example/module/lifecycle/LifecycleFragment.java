package io.github.idonans.example.module.lifecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import io.github.idonans.example.ExampleLog;
import io.github.idonans.example.databinding.FragmentLifecycleBinding;
import io.github.idonans.example.module.uniontype.impl.UnionType;
import io.github.idonans.uniontype.Host;
import io.github.idonans.uniontype.UnionTypeAdapter;
import io.github.idonans.uniontype.UnionTypeItemObject;
import io.github.idonans.uniontype.UnionTypeViewHolder;

public class LifecycleFragment extends Fragment {

    public static LifecycleFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString("text", text);
        LifecycleFragment fragment = new LifecycleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String mText;

    private FragmentLifecycleBinding mBinding;
    UnionTypeAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        if (arg != null) {
            mText = arg.getString("text");
        }
        ExampleLog.v("onCreate %s", mText);
        new LifecycleObserverImpl(mText, getLifecycle());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExampleLog.v("onCreateView %s", mText);

        mBinding = FragmentLifecycleBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExampleLog.v("onViewCreated %s", mText);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mAdapter = new UnionTypeAdapter() {
            @Override
            public boolean onFailedToRecycleView(@NonNull UnionTypeViewHolder holder) {
                ExampleLog.v("onFailedToRecycleView %s %s", mText, holder);
                return super.onFailedToRecycleView(holder);
            }

            @Override
            public void onViewRecycled(@NonNull UnionTypeViewHolder holder) {
                ExampleLog.v("onViewRecycled %s %s", mText, holder);
                super.onViewRecycled(holder);
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
                ExampleLog.v("onAttachedToRecyclerView %s %s", mText, recyclerView);
                super.onAttachedToRecyclerView(recyclerView);
            }

            @Override
            public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
                ExampleLog.v("onDetachedFromRecyclerView %s %s", mText, recyclerView);
                super.onDetachedFromRecyclerView(recyclerView);
            }

            @Override
            public void onViewAttachedToWindow(@NonNull UnionTypeViewHolder holder) {
                ExampleLog.v("onViewAttachedToWindow %s %s", mText, holder);
                super.onViewAttachedToWindow(holder);
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull UnionTypeViewHolder holder) {
                ExampleLog.v("onViewDetachedFromWindow %s %s", mText, holder);
                super.onViewDetachedFromWindow(holder);
            }
        };
        mAdapter.setHost(Host.Factory.create(this, mBinding.recyclerView, mAdapter));
        mAdapter.setUnionTypeMapper(new UnionType());
        mBinding.recyclerView.setAdapter(mAdapter);

        List<UnionTypeItemObject> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new UnionTypeItemObject(UnionType.UNION_TYPE_AUTO_TEXT, mText + "#" + i));
        }
        mAdapter.setGroupItems(1, Lists.newArrayList(items));
    }

    @Override
    public void onStart() {
        super.onStart();
        ExampleLog.v("onStart %s", mText);
    }

    @Override
    public void onResume() {
        super.onResume();
        ExampleLog.v("onResume %s", mText);
    }

    @Override
    public void onPause() {
        super.onPause();
        ExampleLog.v("onPause %s", mText);
    }

    @Override
    public void onStop() {
        super.onStop();
        ExampleLog.v("onStop %s", mText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ExampleLog.v("onDestroyView %s", mText);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ExampleLog.v("onDestroy %s", mText);
    }

}
