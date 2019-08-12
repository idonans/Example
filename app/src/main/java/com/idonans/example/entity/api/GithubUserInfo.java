package com.idonans.example.entity.api;

public class GithubUserInfo {

    public long id;
    public String login;
    public String avatar_url;

    public com.idonans.example.entity.format.GithubUserInfo toFormat() {
        com.idonans.example.entity.format.GithubUserInfo target = new com.idonans.example.entity.format.GithubUserInfo();
        target.id = this.id;
        target.username = this.login;
        target.avatar = this.avatar_url;
        return target;
    }

}
