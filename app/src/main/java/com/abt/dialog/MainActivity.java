package com.abt.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String title = "提示";
    private String content = "直播标题不能为空";
    private String confirm = "确定";

    @OnClick(R.id.button) void clickBtn() {
        DialogFactory.getInstance(MainActivity.this).simpleDialog(title, content);
    }

    @OnClick(R.id.button2) void clickBtn2() {
        String confirm = "结束直播";
        DialogFactory.getInstance(MainActivity.this).normalDialog(title, content, confirm,
                new DialogFactory.ConfirmListener() {
                    @Override
                    public void onConfirmClick(View view, Dialog dialog) {
                        Log.d(TAG, "Btn Confirm Clicked");
                        dialog.dismiss();
                    }
                });
    }

    @OnClick(R.id.button3) void clickBtn3() {
        DialogFactory.getInstance(MainActivity.this).radioGroupConfirmDialog(title, getContents(), confirm);
    }

    @OnClick(R.id.button4) void clickBtn4() {
        DialogFactory.getInstance(MainActivity.this).radioGroupDialog(title, getContents(), confirm,
                new DialogFactory.RadioConfirmListener() {
                    @Override
                    public void onConfirmClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    private CharSequence[] getContents() {
        final CharSequence[] contentList = new CharSequence[3];
        contentList[0] = "11111111";
        contentList[1] = "22222222";
        contentList[2] = "33333333";
        return contentList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

}
