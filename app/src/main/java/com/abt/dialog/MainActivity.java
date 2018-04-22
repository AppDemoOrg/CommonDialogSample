package com.abt.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "提示";
                String content = "直播标题不能为空";
                DialogFactory.getInstance(MainActivity.this).simpleDialog(title, content);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "提示";
                String content = "直播标题不能为空";
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
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "提示";
                final CharSequence[] contentList = new CharSequence[3];
                contentList[0] = "11111111";
                contentList[1] = "22222222";
                contentList[2] = "33333333";
                String confirm = "确定";
                DialogFactory.getInstance(MainActivity.this).radioGroupConfirmDialog(title, contentList, confirm);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "提示";
                final CharSequence[] contentList = new CharSequence[3];
                contentList[0] = "11111111";
                contentList[1] = "22222222";
                contentList[2] = "33333333";
                String confirm = "确定";
                DialogFactory.getInstance(MainActivity.this).radioGroupDialog(title, contentList, confirm,
                        new DialogFactory.RadioConfirmListener() {
                    @Override
                    public void onConfirmClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

}
