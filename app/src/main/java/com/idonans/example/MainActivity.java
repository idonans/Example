package com.idonans.example;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.idonans.example.module.uniontype.UnionTypeActivity;
import com.idonans.lang.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.item_union_type)
    View mItemUnionType;
    @BindView(R.id.item_more)
    View mItemMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewUtil.onClick(mItemUnionType, v -> UnionTypeActivity.start(this));
        ViewUtil.onClick(mItemMore, v -> {
            // TODO
        });
    }

}
