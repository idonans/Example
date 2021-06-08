package io.github.idonans.example.module.uniontype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;

import io.github.idonans.dynamic.DynamicResult;
import io.github.idonans.dynamic.page.PagePresenter;
import io.github.idonans.dynamic.page.UnionTypeStatusPageView;
import io.github.idonans.example.ExampleLog;
import io.github.idonans.example.api.DefaultApi;
import io.github.idonans.example.entity.format.GithubUserInfo;
import io.github.idonans.example.module.uniontype.impl.UnionType;
import io.github.idonans.uniontype.UnionTypeItemObject;
import io.reactivex.rxjava3.core.SingleSource;

public class MyPresenter extends PagePresenter<UnionTypeItemObject, Object, UnionTypeStatusPageView<Object>> {

    private final String mSearch = "idona";
    private int mPrePageNo;
    private int mNextPageNo;

    public MyPresenter(UnionTypeActivity.UnionTypePageView view) {
        super(view);
    }

    @Nullable
    @Override
    protected SingleSource<DynamicResult<UnionTypeItemObject, Object>> createInitRequest() throws Exception {
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
                .map(input -> new DynamicResult<UnionTypeItemObject, Object>().setItems(input))
                .firstOrError();
    }

    @Override
    protected void onInitRequestResult(@NonNull UnionTypeStatusPageView<Object> view, @NonNull DynamicResult<UnionTypeItemObject, Object> result) {
        ExampleLog.v("onInitRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        if (result.items != null && !result.items.isEmpty()) {
            setPrePageRequestEnable(true);
            setNextPageRequestEnable(true);
            mPrePageNo = 1;
            mNextPageNo = 1;
        } else {
            setPrePageRequestEnable(false);
            setNextPageRequestEnable(false);
        }

        super.onInitRequestResult(view, result);
    }

    @Nullable
    @Override
    protected SingleSource<DynamicResult<UnionTypeItemObject, Object>> createPrePageRequest() throws Exception {
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
                .map(input -> new DynamicResult<UnionTypeItemObject, Object>().setItems(input))
                .firstOrError();
    }

    @Override
    protected void onPrePageRequestResult(@NonNull UnionTypeStatusPageView<Object> view, @NonNull DynamicResult<UnionTypeItemObject, Object> result) {
        ExampleLog.v("onPrePageRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        if (result.items != null && !result.items.isEmpty()) {
            mPrePageNo--;
        }

        super.onPrePageRequestResult(view, result);
    }

    @Nullable
    @Override
    protected SingleSource<DynamicResult<UnionTypeItemObject, Object>> createNextPageRequest() throws Exception {
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
                .map(input -> new DynamicResult<UnionTypeItemObject, Object>().setItems(input))
                .firstOrError();
    }

    @Override
    protected void onNextPageRequestResult(@NonNull UnionTypeStatusPageView<Object> view, @NonNull DynamicResult<UnionTypeItemObject, Object> result) {
        ExampleLog.v("onNextPageRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        if (result.items != null) {
            mNextPageNo++;
        }

        super.onNextPageRequestResult(view, result);
    }

}
