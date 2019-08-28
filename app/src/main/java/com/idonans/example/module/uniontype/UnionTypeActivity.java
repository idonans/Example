package com.idonans.example.module.uniontype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idonans.dynamic.page.UnionTypeStatusPageView;
import com.idonans.example.R;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.example.widget.pulllayout.PullLayout;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnionTypeActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, UnionTypeActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.refresh_layout)
    PullLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private UnionTypePageView mPageView;
    private MyPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union_type);
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        UnionTypeAdapter adapter = new UnionTypeAdapter();
        adapter.setHost(Host.Factory.create(this, mRecyclerView, adapter));
        adapter.setUnionTypeMapper(new UnionType());
        mRecyclerView.setAdapter(adapter);

        mPageView = new UnionTypePageView(adapter) {
            @Override
            public void showInitLoading() {
                super.showInitLoading();
            }

            @Override
            public void hideInitLoading() {
                super.hideInitLoading();
                if (mRefreshLayout != null) {
                    mRefreshLayout.setRefreshing(false, true);
                }
            }
        };
        mPresenter = new MyPresenter(mPageView);
        mPageView.setPresenter(mPresenter);
        mPresenter.requestInit();

        mRefreshLayout.setOnRefreshListener(pullLayout -> {
            if (mPresenter != null) {
                mPresenter.requestInit(true);
                return;
            }

            pullLayout.setRefreshing(false, false);
        });
    }

    public class UnionTypePageView extends UnionTypeStatusPageView {
        public UnionTypePageView(@NonNull UnionTypeAdapter adapter) {
            super(adapter);
        }
    }

}
