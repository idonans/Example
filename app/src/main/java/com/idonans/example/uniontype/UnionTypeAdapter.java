package com.idonans.example.uniontype;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.base.Preconditions;

public class UnionTypeAdapter<T extends UnionTypeViewHolder> extends RecyclerView.Adapter<T> {

    private Host mHost;
    private UnionTypeMapper mUnionTypeMapper;

    public void setHost(@NonNull Host host) {
        mHost = host;
    }

    public void setUnionTypeMapper(@NonNull UnionTypeMapper unionTypeMapper) {
        mUnionTypeMapper = unionTypeMapper;

    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Preconditions.checkNotNull(parent);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
