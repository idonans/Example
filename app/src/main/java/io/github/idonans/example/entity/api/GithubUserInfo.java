package io.github.idonans.example.entity.api;

public class GithubUserInfo {

    public long id;
    public String login;
    public String avatar_url;

    public io.github.idonans.example.entity.format.GithubUserInfo toFormat() {
        io.github.idonans.example.entity.format.GithubUserInfo target = new io.github.idonans.example.entity.format.GithubUserInfo();
        target.id = this.id;
        target.username = this.login;
        target.avatar = this.avatar_url;
        return target;
    }

}
