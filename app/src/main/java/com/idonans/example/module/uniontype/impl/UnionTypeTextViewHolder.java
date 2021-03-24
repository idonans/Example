package com.idonans.example.module.uniontype.impl;

import androidx.annotation.NonNull;

import com.idonans.example.R;
import com.idonans.example.databinding.UnionTypeTextViewHolderBinding;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeViewHolder;

public class UnionTypeTextViewHolder extends UnionTypeViewHolder<Object> {

    private final UnionTypeTextViewHolderBinding mBinding;

    public UnionTypeTextViewHolder(@NonNull Host host) {
        super(host, R.layout.union_type_text_view_holder);
        mBinding = UnionTypeTextViewHolderBinding.bind(itemView);
    }

    @Override
    public void onBind(int position, Object itemObject) {
        mBinding.text.setText(String.valueOf(itemObject));
    }

}
