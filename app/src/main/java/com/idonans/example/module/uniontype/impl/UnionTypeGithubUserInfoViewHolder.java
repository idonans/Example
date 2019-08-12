package com.idonans.example.module.uniontype.impl;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.drawee.view.SimpleDraweeView;
import com.idonans.example.R;
import com.idonans.example.entity.format.GithubUserInfo;
import com.idonans.uniontype.Host;
import com.idonans.uniontype.UnionTypeViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnionTypeGithubUserInfoViewHolder extends UnionTypeViewHolder<GithubUserInfo> {

    @BindView(R.id.avatar)
    SimpleDraweeView mAvatar;
    @BindView(R.id.username)
    TextView mUsername;

    public UnionTypeGithubUserInfoViewHolder(@NonNull Host host) {
        super(host, R.layout.union_type_github_user_info_view_holder);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(int position, GithubUserInfo itemObject) {
        mAvatar.setImageURI(itemObject.avatar);
        mUsername.setText(itemObject.username);
    }

}
