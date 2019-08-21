package com.idonans.example.module.uniontype.impl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.idonans.example.R;
import com.idonans.lang.util.ViewUtil;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class UnionTypeAutoTextViewHolder extends UnionTypeViewHolder<Object> {

    @BindView(R.id.text_1)
    TextView mText;
    @BindView(R.id.action_refresh)
    View mActionRefresh;

    public UnionTypeAutoTextViewHolder(@NonNull Host host) {
        super(host, R.layout.union_type_auto_text_view_holder);
        ButterKnife.bind(this, itemView);
        ViewUtil.onClick(mActionRefresh, v -> {
            int position = getAdapterPosition();
            if (position >= 0) {
                host.getAdapter().notifyItemChanged(position);
            }
        });
        Timber.v("init %s @%s", mLastText, hashCode());
    }

    private String mLastText;

    @Override
    public void onBind(int position, Object itemObject) {
        String oldLastText = mLastText;
        String time = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss:SSS]", Locale.getDefault()).format(new Date());
        mLastText = itemObject + " " + time;
        mText.setText(mLastText);

        Timber.v("onBind %s -> %s @%s", oldLastText, mLastText, hashCode());
    }

    @Override
    public String toString() {
        return mLastText + "@" + hashCode();
    }

}
