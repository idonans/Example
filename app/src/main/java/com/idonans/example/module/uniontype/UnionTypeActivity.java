package com.idonans.example.module.uniontype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idonans.dynamic.page.PagePresenter;
import com.idonans.dynamic.page.PageView;
import com.idonans.example.R;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.uniontype.GroupArrayList;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeAdapter;
import com.idonans.uniontype.UnionTypeItemObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class UnionTypeActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, UnionTypeActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private UnionTypePageView mUnionTypePageView;
    private UnionTypePresenter mUnionTypePresenter;

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

        mUnionTypePageView = new UnionTypePageView(adapter);
        mUnionTypePresenter = new UnionTypePresenter(mUnionTypePageView);
        mUnionTypePageView.setPresenter(mUnionTypePresenter);
        mUnionTypePresenter.requestInit();
    }

    public class UnionTypePageView implements PageView<UnionTypeItemObject> {

        private static final int GROUP_DEFAULT = 1;

        private final UnionTypeAdapter mUnionTypeAdapter;
        private PagePresenter<UnionTypeItemObject, UnionTypePageView> mPresenter;

        public UnionTypePageView(UnionTypeAdapter unionTypeAdapter) {
            mUnionTypeAdapter = unionTypeAdapter;
            mUnionTypeAdapter.setOnLoadPrePageListener(() -> {
                Timber.v("setOnLoadPrePageListener callback");
                if (mPresenter == null) {
                    Timber.e("presenter is null");
                    return;
                }
                mPresenter.requestPrePage();
            });
            mUnionTypeAdapter.setOnLoadNextPageListener(() -> {
                Timber.v("setOnLoadNextPageListener callback");
                if (mPresenter == null) {
                    Timber.e("presenter is null");
                    return;
                }
                mPresenter.requestNextPage();
            });
        }

        public void setPresenter(PagePresenter<UnionTypeItemObject, UnionTypePageView> presenter) {
            mPresenter = presenter;
        }

        @Override
        public void showInitLoading() {

        }

        @Override
        public void hideInitLoading() {

        }

        @Override
        public void showPrePageLoading() {

        }

        @Override
        public void hidePrePageLoading() {

        }

        @Override
        public void showNextPageLoading() {

        }

        @Override
        public void hideNextPageLoading() {

        }

        @Override
        public void onInitDataLoad(Collection<UnionTypeItemObject> items) {
            GroupArrayList data = new GroupArrayList();
            data.setGroupItems(GROUP_DEFAULT, items);
            mUnionTypeAdapter.setData(data);
        }

        @Override
        public void onInitDataLoadFail(Throwable e) {

        }

        @Override
        public void onPrePageDataLoad(Collection<UnionTypeItemObject> items) {
            mUnionTypeAdapter.insertGroupItems(GROUP_DEFAULT, 0, items);
        }

        @Override
        public void onPrePageDataLoadFail(Throwable e) {

        }

        @Override
        public void onNextPageDataLoad(Collection<UnionTypeItemObject> items) {
            mUnionTypeAdapter.appendGroupItems(GROUP_DEFAULT, items);
        }

        @Override
        public void onNextPageDataLoadFail(Throwable e) {

        }
    }

}
