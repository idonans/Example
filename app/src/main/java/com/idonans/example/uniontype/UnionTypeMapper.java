package com.idonans.example.uniontype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface UnionTypeMapper {

    @Nullable
    UnionTypeViewHolder map(@NonNull Host host, int unionType);

}
