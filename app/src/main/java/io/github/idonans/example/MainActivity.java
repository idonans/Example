package io.github.idonans.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.idonans.backstack.dialog.ViewDialog;
import io.github.idonans.example.databinding.ActivityMainBinding;
import io.github.idonans.example.module.lifecycle.LifecycleActivity;
import io.github.idonans.example.module.uniontype.UnionTypeActivity;
import com.idonans.lang.util.ViewUtil;

import static io.github.idonans.example.databinding.ActivityMainBinding.inflate;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ViewUtil.onClick(mBinding.itemUnionType, v -> UnionTypeActivity.start(this));
        ViewUtil.onClick(mBinding.itemViewDialog, v -> showSimpleDialog(0));
        ViewUtil.onClick(mBinding.itemLifecycle, v -> LifecycleActivity.start(this));
        ViewUtil.onClick(mBinding.itemMore, v -> {
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
