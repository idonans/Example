package com.idonans.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.idonans.backstack.dialog.ViewDialog;
import com.idonans.example.module.uniontype.UnionTypeActivity;
import com.idonans.lang.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.item_union_type)
    View mItemUnionType;
    @BindView(R.id.item_view_dialog)
    View mItemViewDialog;
    @BindView(R.id.item_more)
    View mItemMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewUtil.onClick(mItemUnionType, v -> UnionTypeActivity.start(this));
        ViewUtil.onClick(mItemViewDialog, v -> {
            showSimpleDialog(0);
        });
        ViewUtil.onClick(mItemMore, v -> {
            // TODO
        });
    }

    private void showSimpleDialog(int count) {
        new SimpleDialog(this, count).show();
    }

    private class SimpleDialog {

        private ViewDialog mViewDialog;
        private TextView mText;

        public SimpleDialog(Activity activity, int count) {
            mViewDialog = new ViewDialog.Builder(activity)
                    .setParentView(activity.findViewById(Window.ID_ANDROID_CONTENT))
                    .setContentView(R.layout.simple_dialog)
                    .defaultAnimation()
                    .setCancelable(true)
                    .create();
            mText = mViewDialog.findViewById(R.id.text);
            mText.setText("#" + count + ", click here");
            ViewUtil.onClick(mText, v -> {
                hide();
                showSimpleDialog(count + 1);
            });
        }

        public void show() {
            mViewDialog.show();
        }

        public void hide() {
            mViewDialog.hide(false);
        }
    }

}
