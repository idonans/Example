package io.github.idonans.example.module.uniontype.impl;

import androidx.annotation.NonNull;

import io.github.idonans.core.util.Preconditions;
import io.github.idonans.example.R;
import io.github.idonans.example.databinding.UnionTypeGithubUserInfoViewHolderBinding;
import io.github.idonans.example.entity.format.GithubUserInfo;
import io.github.idonans.uniontype.Host;
import io.github.idonans.uniontype.UnionTypeViewHolder;

public class UnionTypeGithubUserInfoViewHolder extends UnionTypeViewHolder {

    private final UnionTypeGithubUserInfoViewHolderBinding mBinding;

    public UnionTypeGithubUserInfoViewHolder(@NonNull Host host) {
        super(host, R.layout.union_type_github_user_info_view_holder);
        mBinding = UnionTypeGithubUserInfoViewHolderBinding.bind(itemView);
    }

    @Override
    public void onBindUpdate() {
        final GithubUserInfo itemObject = (GithubUserInfo) this.itemObject;
        Preconditions.checkNotNull(itemObject);
        mBinding.avatar.setImageURI(itemObject.avatar);
        mBinding.username.setText(itemObject.username);
    }

}
