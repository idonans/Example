package com.idonans.example.module.uniontype.impl;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.idonans.example.R;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnionTypeTextViewHolder extends UnionTypeViewHolder<Object> {

    @BindView(R.id.text)
    TextView mText;

    public UnionTypeTextViewHolder(@NonNull Host host) {
        super(host, R.layout.union_type_text_view_holder);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(int position, Object itemObject) {
        mText.setText(String.valueOf(itemObject));
    }

}
