package io.github.idonans.example.module.uniontype.impl;

import androidx.annotation.NonNull;

import io.github.idonans.example.ExampleLog;
import io.github.idonans.example.R;
import io.github.idonans.example.databinding.UnionTypeAutoTextViewHolderBinding;
import com.idonans.lang.util.ViewUtil;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UnionTypeAutoTextViewHolder extends UnionTypeViewHolder {

    private final UnionTypeAutoTextViewHolderBinding mBinding;

    public UnionTypeAutoTextViewHolder(@NonNull Host host) {
        super(host, R.layout.union_type_auto_text_view_holder);
        mBinding = UnionTypeAutoTextViewHolderBinding.bind(itemView);
        ViewUtil.onClick(mBinding.actionRefresh, v -> {
            int position = getAdapterPosition();
            if (position >= 0) {
                host.getAdapter().notifyItemChanged(position);
            }
        });
        ExampleLog.v("init %s @%s", mLastText, hashCode());
    }

    private String mLastText;

    @Override
    public void onBind(int position, @NonNull Object itemObject) {
        String oldLastText = mLastText;
        String time = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss:SSS]", Locale.getDefault()).format(new Date());
        mLastText = itemObject + " " + time;
        mBinding.text1.setText(mLastText);

        ExampleLog.v("onBind %s -> %s @%s", oldLastText, mLastText, hashCode());
    }

    @Override
    @NonNull
    public String toString() {
        return mLastText + "@" + hashCode();
    }

}
