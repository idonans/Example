package com.idonans.example.api;

import androidx.annotation.IntRange;

import com.idonans.example.entity.api.GithubPage;
import com.idonans.example.entity.api.GithubUserInfo;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DefaultApiInterface {

    @GET("https://api.github.com/search/users?per_page=10")
    @Headers({
            "Accept: application/vnd.github.v3+json"
    })
    Observable<GithubPage<GithubUserInfo>> searchUserInfo(
            @Query("q") String search,
            @IntRange(from = 1) @Query("page") int pageNo);

}
