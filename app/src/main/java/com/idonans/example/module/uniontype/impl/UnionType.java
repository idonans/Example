package com.idonans.example.module.uniontype.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.idonans.example.uniontype.Host;
import com.idonans.example.uniontype.UnionTypeMapper;
import com.idonans.example.uniontype.UnionTypeViewHolder;

public class UnionType implements UnionTypeMapper {

    public static final int UNION_TYPE_TEXT = 1;

    @Nullable
    @Override
    public UnionTypeViewHolder map(@NonNull Host host, int unionType) {
        switch (unionType) {
            case UNION_TYPE_TEXT:
                return new UnionTypeTextViewHolder(host);
            default:
                return null;
        }
    }

}
