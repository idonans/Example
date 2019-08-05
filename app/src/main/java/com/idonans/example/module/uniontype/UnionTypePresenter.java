package com.idonans.example.module.uniontype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.idonans.dynamic.page.PagePresenter;
import com.idonans.dynamic.page.PageView;
import com.idonans.example.module.uniontype.impl.UnionType;
import com.idonans.lang.thread.Threads;
import com.idonans.uniontype.UnionTypeItemObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;

public class UnionTypePresenter extends PagePresenter<UnionTypeItemObject, UnionTypeActivity.UnionTypePageView> {

    private int mPrePageNo;
    private int mNextPageNo;

    public UnionTypePresenter(UnionTypeActivity.UnionTypePageView view) {
        super(view);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createInitRequest() throws Exception {
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
        mPrePageNo = 0;
        mNextPageNo = 0;
        super.onInitRequestResult(view, items);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createPrePageRequest() throws Exception {
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
        mPrePageNo--;
        super.onPrePageRequestResult(view, items);
    }

    @Nullable
    @Override
    protected SingleSource<Collection<UnionTypeItemObject>> createNextPageRequest() throws Exception {
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
        mNextPageNo++;
        super.onNextPageRequestResult(view, items);
    }

}
