package com.idonans.example.module.uniontype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.idonans.dynamic.page.PageView;
import com.idonans.dynamic.page.StatusPagePresenter;
import com.idonans.dynamic.page.uniontype.UnionTypeStatusPageView;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.lang.thread.Threads;
import com.idonans.uniontype.UnionTypeItemObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import timber.log.Timber;

public class MyPresenter extends StatusPagePresenter<UnionTypeItemObject, UnionTypeStatusPageView> {

    private int mPrePageNo;
    private int mNextPageNo;

    public MyPresenter(UnionTypeActivity.UnionTypePageView view) {
        super(view, true, true);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createInitRequest() throws Exception {
        Timber.v("createInitRequest [%s, %s]", mPrePageNo, mNextPageNo);

        return Single.fromCallable(() -> {
            Threads.sleepQuietly(2000L);
            List<UnionTypeItemObject> items = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                items.add(UnionTypeItemObject.valueOf(UnionType.UNION_TYPE_TEXT, "[page 0]#" + i + "/10"));
            }
            return items;
        });
    }

    @Override
    protected void onInitRequestResult(@NonNull PageView<UnionTypeItemObject> view, @NonNull Collection<UnionTypeItemObject> items) {
        Timber.v("onInitRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        mPrePageNo = 0;
        mNextPageNo = 0;
        super.onInitRequestResult(view, items);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createPrePageRequest() throws Exception {
        Timber.v("createPrePageRequest [%s, %s]", mPrePageNo, mNextPageNo);

        return Single.fromCallable(() -> {
            Threads.sleepQuietly(2000L);
            List<UnionTypeItemObject> items = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                items.add(UnionTypeItemObject.valueOf(UnionType.UNION_TYPE_TEXT, "[page " + (mPrePageNo - 1) + "]#" + i + "/10"));
            }
            return items;
        });
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

        return Single.fromCallable(() -> {
            Threads.sleepQuietly(2000L);
            List<UnionTypeItemObject> items = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                items.add(UnionTypeItemObject.valueOf(UnionType.UNION_TYPE_TEXT, "[page " + (mNextPageNo + 1) + "]#" + i + "/10"));
            }
            return items;
        });
    }

    @Override
    protected void onNextPageRequestResult(@NonNull PageView<UnionTypeItemObject> view, @NonNull Collection<UnionTypeItemObject> items) {
        Timber.v("onNextPageRequestResult [%s, %s]", mPrePageNo, mNextPageNo);

        mNextPageNo++;
        super.onNextPageRequestResult(view, items);
    }

}
