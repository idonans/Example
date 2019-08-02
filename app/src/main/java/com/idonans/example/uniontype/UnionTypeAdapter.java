package com.idonans.example.uniontype;

import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class UnionTypeAdapter extends RecyclerView.Adapter<UnionTypeViewHolder> {

    private final List<UnionTypeItemObject> mData = new ArrayList<>();
    private Host mHost;
    private UnionTypeMapper mUnionTypeMapper;

    public void setHost(@NonNull Host host) {
        mHost = host;
    }

    public void setUnionTypeMapper(@NonNull UnionTypeMapper unionTypeMapper) {
        mUnionTypeMapper = unionTypeMapper;
    }

    public Host getHost() {
        return mHost;
    }

    public UnionTypeMapper getUnionTypeMapper() {
        return mUnionTypeMapper;
    }

    @NonNull
    @Override
    public UnionTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Preconditions.checkNotNull(mUnionTypeMapper);
        Preconditions.checkNotNull(mHost);

        UnionTypeViewHolder viewHolder = mUnionTypeMapper.map(mHost, viewType);
        if (viewHolder == null) {
            viewHolder = new NullUnionTypeViewHolder(mHost);
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        UnionTypeItemObject itemObject = getItem(position);
        if (itemObject == null) {
            return UnionTypeMapper.UNION_TYPE_NULL;
        }
        return itemObject.unionType;
    }

    @Override
    public void onBindViewHolder(@NonNull UnionTypeViewHolder holder, int position) {
        UnionTypeItemObject itemObject = getItem(position);
        if (itemObject != null) {
            holder.onBind(position, itemObject.itemObject);
        }

        if (mOnLoadPrePageListener != null) {
            if (position <= mLoadPrePageOffset) {
                mOnLoadPrePageListener.onLoadPrePage();
            }
        }
        if (mOnLoadNextPageListener != null) {
            int count = getItemCount();
            if (count - position - 1 <= mLoadNextPageOffset) {
                mOnLoadNextPageListener.onLoadNextPage();
            }
        }

    }

    @Nullable
    public UnionTypeItemObject getItem(int position) {
        int size = mData.size();
        if (position >= 0 && position < size) {
            return mData.get(position);
        }
        return null;
    }

    @NonNull
    public List<UnionTypeItemObject> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 加载上一页
     */
    public interface OnLoadPrePageListener {
        void onLoadPrePage();
    }

    private int mLoadPrePageOffset = 5;
    private OnLoadPrePageListener mOnLoadPrePageListener;

    public void setLoadPrePageOffset(@IntRange(from = 0) int offset) {
        mLoadPrePageOffset = offset;
    }

    public void setOnLoadPrePageListener(OnLoadPrePageListener listener) {
        mOnLoadPrePageListener = listener;
    }

    /**
     * 加载下一页
     */
    public interface OnLoadNextPageListener {
        void onLoadNextPage();
    }

    private int mLoadNextPageOffset = 5;
    private OnLoadNextPageListener mOnLoadNextPageListener;

    public void setLoadNextPageOffset(@IntRange(from = 0) int offset) {
        mLoadNextPageOffset = offset;
    }

    public void setOnLoadNextPageListener(OnLoadNextPageListener listener) {
        mOnLoadNextPageListener = listener;
    }
}
