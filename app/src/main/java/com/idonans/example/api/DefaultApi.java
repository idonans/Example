package com.idonans.example.api;

import androidx.annotation.IntRange;

import com.idonans.example.entity.format.GithubPage;
import com.idonans.example.entity.format.GithubUserInfo;
import com.idonans.lang.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultApi {

    private static final Singleton<DefaultApi> sInstance = new Singleton<DefaultApi>() {
        @Override
        protected DefaultApi create() {
            return new DefaultApi();
        }
    };

    public static DefaultApi getInstance() {
        return sInstance.get();
    }

    private final DefaultApiInterface mDefaultApiInterface;

    private DefaultApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mDefaultApiInterface = retrofit.create(DefaultApiInterface.class);
    }

    public Observable<GithubPage<GithubUserInfo>> searchUserInfo(String search, @IntRange(from = 1) int pageNo) {
        return mDefaultApiInterface.searchUserInfo(search, pageNo)
                .map(input -> input.toFormat(com.idonans.example.entity.api.GithubUserInfo::toFormat));
    }

}
