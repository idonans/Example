package com.idonans.example.module.uniontype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.idonans.dynamic.page.UnionTypeStatusPageView;
import com.idonans.example.databinding.ActivityUnionTypeBinding;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeAdapter;

public class UnionTypeActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, UnionTypeActivity.class);
        context.startActivity(starter);
    }

    private ActivityUnionTypeBinding mBinding;
    private UnionTypePageView mPageView;
    private MyPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUnionTypeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UnionTypeAdapter adapter = new UnionTypeAdapter();
        adapter.setHost(Host.Factory.create(this, mBinding.recyclerView, adapter));
        adapter.setUnionTypeMapper(new UnionType());
        mBinding.recyclerView.setAdapter(adapter);

        mPageView = new UnionTypePageView(adapter);
        mPresenter = new MyPresenter(mPageView);
        mPageView.setPresenter(mPresenter);
        mPresenter.requestInit();

        mBinding.pullLayout.setOnRefreshListener(pullLayout -> {
            if (mPresenter != null) {
                mPresenter.requestInit(true);
            }
        });
    }

    public class UnionTypePageView extends UnionTypeStatusPageView {
        public UnionTypePageView(@NonNull UnionTypeAdapter adapter) {
            super(adapter);
        }

        @Override
        public void showInitLoading() {
            if (!hasPageContent()) {
                super.showInitLoading();
                if (mBinding.pullLayout != null) {
                    mBinding.pullLayout.setRefreshing(false, false);
                    mBinding.pullLayout.setEnabled(false);
                }
            } else {
                super.hideInitLoading();
                if (mBinding.pullLayout != null) {
                    mBinding.pullLayout.setRefreshing(true, false);
                    mBinding.pullLayout.setEnabled(true);
                }
            }
        }

        @Override
        public void hideInitLoading() {
            super.hideInitLoading();
            if (mBinding.pullLayout != null) {
                mBinding.pullLayout.setRefreshing(false, false);
                mBinding.pullLayout.setEnabled(true);
            }
        }
    }

}
