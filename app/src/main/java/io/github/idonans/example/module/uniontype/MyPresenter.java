package io.github.idonans.example.module.uniontype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;

import io.github.idonans.dynamic.page.PagePresenter;
import io.github.idonans.dynamic.page.PageView;
import io.github.idonans.dynamic.page.UnionTypeStatusPageView;
import io.github.idonans.example.ExampleLog;
import io.github.idonans.example.api.DefaultApi;
import io.github.idonans.example.entity.format.GithubUserInfo;
import io.github.idonans.example.module.uniontype.impl.UnionType;
import io.github.idonans.uniontype.UnionTypeItemObject;
import io.reactivex.rxjava3.core.SingleSource;

public class MyPresenter extends PagePresenter<UnionTypeItemObject, UnionTypeStatusPageView> {

    private final String mSearch = "idona";
    private int mPrePageNo;
    private int mNextPageNo;

    public MyPresenter(UnionTypeActivity.UnionTypePageView view) {
        super(view, true, true);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createInitRequest() throws Exception {
        ExampleLog.v("createInitRequest [%s, %s]", mPrePageNo, mNextPageNo);

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
        ExampleLog.v("onInitRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

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
        ExampleLog.v("createPrePageRequest [%s, %s]", mPrePageNo, mNextPageNo);

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
        ExampleLog.v("onPrePageRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        mPrePageNo--;
        super.onPrePageRequestResult(view, items);
    }

    @Override
    protected void onPrePageRequestError(@NonNull PageView<UnionTypeItemObject> view, @NonNull Throwable e) {
        super.onPrePageRequestError(view, e);
        e.printStackTrace();
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createNextPageRequest() throws Exception {
        ExampleLog.v("createNextPageRequest [%s, %s]", mPrePageNo, mNextPageNo);

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
        ExampleLog.v("onNextPageRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        mNextPageNo++;
        super.onNextPageRequestResult(view, items);
    }

    @Override
    protected void onNextPageRequestError(@NonNull PageView<UnionTypeItemObject> view, @NonNull Throwable e) {
        super.onNextPageRequestError(view, e);
        e.printStackTrace();
    }

}
