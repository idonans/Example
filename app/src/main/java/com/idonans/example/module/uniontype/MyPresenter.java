package com.idonans.example.module.uniontype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.idonans.dynamic.page.PageView;
import com.idonans.dynamic.page.StatusPagePresenter;
import com.idonans.dynamic.page.uniontype.UnionTypeStatusPageView;
import com.idonans.example.api.DefaultApi;
import com.idonans.example.entity.format.GithubUserInfo;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.uniontype.UnionTypeItemObject;

import java.util.ArrayList;
import java.util.Collection;

import io.reactivex.SingleSource;
import timber.log.Timber;

public class MyPresenter extends StatusPagePresenter<UnionTypeItemObject, UnionTypeStatusPageView> {

    private final String mSearch = "idona";
    private int mPrePageNo;
    private int mNextPageNo;

    public MyPresenter(UnionTypeActivity.UnionTypePageView view) {
        super(view, true, true);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createInitRequest() throws Exception {
        Timber.v("createInitRequest [%s, %s]", mPrePageNo, mNextPageNo);

        return DefaultApi.getInstance().searchUserInfo(mSearch, 1)
                .map(input -> {
                    Collection<UnionTypeItemObject> items = new ArrayList<>();
                    if (input.items != null) {
                        for (GithubUserInfo item : input.items) {
                            if (item == null) {
                                continue;
                            }
                            items.add(UnionTypeItemObject.valueOf(UnionType.UNION_TYPE_GITHUB_USER_INFO, item));
                        }
                    }
                    return items;
                })
                .firstOrError();
    }

    @Override
    protected void onInitRequestResult(@NonNull PageView<UnionTypeItemObject> view, @NonNull Collection<UnionTypeItemObject> items) {
        Timber.v("onInitRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        mPrePageNo = 1;
        mNextPageNo = 1;
        super.onInitRequestResult(view, items);
    }

    @Override
    protected void onInitRequestError(@NonNull PageView<UnionTypeItemObject> view, @NonNull Throwable e) {
        super.onInitRequestError(view, e);
        e.printStackTrace();
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createPrePageRequest() throws Exception {
        Timber.v("createPrePageRequest [%s, %s]", mPrePageNo, mNextPageNo);

        return DefaultApi.getInstance().searchUserInfo(mSearch, mPrePageNo - 1)
                .map(input -> {
                    Collection<UnionTypeItemObject> items = new ArrayList<>();
                    if (input.items != null) {
                        for (GithubUserInfo item : input.items) {
                            if (item == null) {
                                continue;
                            }
                            items.add(UnionTypeItemObject.valueOf(UnionType.UNION_TYPE_GITHUB_USER_INFO, item));
                        }
                    }
                    return items;
                })
                .firstOrError();
    }

    @Override
    protected void onPrePageRequestResult(@NonNull PageView<UnionTypeItemObject> view, @NonNull Collection<UnionTypeItemObject> items) {
        Timber.v("onPrePageRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        mPrePageNo--;
        super.onPrePageRequestResult(view, items);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createNextPageRequest() throws Exception {
        Timber.v("createNextPageRequest [%s, %s]", mPrePageNo, mNextPageNo);

        return DefaultApi.getInstance().searchUserInfo(mSearch, mNextPageNo + 1)
                .map(input -> {
                    Collection<UnionTypeItemObject> items = new ArrayList<>();
                    if (input.items != null) {
                        for (GithubUserInfo item : input.items) {
                            if (item == null) {
                                continue;
                            }
                            items.add(UnionTypeItemObject.valueOf(UnionType.UNION_TYPE_GITHUB_USER_INFO, item));
                        }
                    }
                    return items;
                })
                .firstOrError();
    }

    @Override
    protected void onNextPageRequestResult(@NonNull PageView<UnionTypeItemObject> view, @NonNull Collection<UnionTypeItemObject> items) {
        Timber.v("onNextPageRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        mNextPageNo++;
        super.onNextPageRequestResult(view, items);
    }

}
