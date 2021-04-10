package io.github.idonans.example.module.uniontype.impl;

import androidx.annotation.NonNull;

import io.github.idonans.example.R;
import io.github.idonans.example.databinding.UnionTypeTextViewHolderBinding;
import io.github.idonans.uniontype.Host;
import io.github.idonans.uniontype.UnionTypeViewHolder;

public class UnionTypeTextViewHolder extends UnionTypeViewHolder {

    private final UnionTypeTextViewHolderBinding mBinding;

    public UnionTypeTextViewHolder(@NonNull Host host) {
        super(host, R.layout.union_type_text_view_holder);
        mBinding = UnionTypeTextViewHolderBinding.bind(itemView);
    }

    @Override
    public void onBind(int position, @NonNull Object itemObject) {
        mBinding.text.setText(String.valueOf(itemObject));
    }

}
