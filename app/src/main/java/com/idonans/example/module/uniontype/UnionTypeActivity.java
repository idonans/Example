package com.idonans.example.module.uniontype;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idonans.example.R;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.example.uniontype.Host;
import com.idonans.example.uniontype.UnionTypeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnionTypeActivity extends AppCompatActivity {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

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

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {

        });
    }


}
