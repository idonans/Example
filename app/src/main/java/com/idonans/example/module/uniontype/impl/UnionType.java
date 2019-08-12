package com.idonans.example.module.uniontype.impl;

import com.idonans.dynamic.page.uniontype.UnionTypePageStatus;

public class UnionType extends UnionTypePageStatus {

    public static final int UNION_TYPE_TEXT = 1;
    public static final int UNION_TYPE_GITHUB_USER_INFO = 2;

    public UnionType() {
        put(UNION_TYPE_TEXT, UnionTypeTextViewHolder::new);
        put(UNION_TYPE_GITHUB_USER_INFO, UnionTypeGithubUserInfoViewHolder::new);
    }

}
