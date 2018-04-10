package com.abt.custom_various_dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/16 0016.
 */

public class DialogFactory {

    private static final String TAG = DialogFactory.class.getSimpleName();
    private static Context mContext; // TODO static context 有内存泄露的风险

    private DialogFactory() { }

    private static class NestClass {
        private static DialogFactory instance;
        static {
            instance = new DialogFactory();
        }
    }

    public static DialogFactory getInstance(Context context) {
        mContext = context;
        return NestClass.instance;
    }

    public void simpleDialog(String title, String content) {

        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_layout_simple, (ViewGroup) ((Activity)mContext).findViewById(R.id.dialog_simple));
        ((TextView) dialog.findViewById(R.id.title)).setText(title);
        ((TextView) dialog.findViewById(R.id.content)).setText(content);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final Dialog dialogA = builder.setView(dialog).create();
        dialogA.show();

        dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "Btn Confirm Clicked");
                dialogA.dismiss();
            }
        });
    }

    public Dialog normalDialog(String title, String content, String confirm, final ConfirmListener listener) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_layout_normal, (ViewGroup) ((Activity)mContext).findViewById(R.id.dialog_normal));
        ((TextView) dialog.findViewById(R.id.title)).setText(title);
        ((TextView) dialog.findViewById(R.id.content)).setText(content);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final Dialog dialogA = builder.setView(dialog).create();
        dialogA.show();

        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "Btn Cancel Clicked");
                dialogA.dismiss();
            }
        });

        ((Button) dialog.findViewById(R.id.confirm)).setText(confirm);
        dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onConfirmClick(v, dialogA);
            }
        });

        return dialogA;
    }

    public interface ConfirmListener {
        void onConfirmClick(View view, Dialog dialog);
    }

    public interface RadioConfirmListener {
        void onConfirmClick(DialogInterface dialog, int which);
    }

    public void radioGroupDialog(String title, final CharSequence[] contentList, String confirm, final RadioConfirmListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setSingleChoiceItems(contentList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "single choice items click = "+which);
                listener.onConfirmClick(dialog, which);
            }
        });
        builder.show();
    }

    private static int index;
    public Dialog radioGroupConfirmDialog(String title, final CharSequence[] contentList, String confirm) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_layout_normal, (ViewGroup) ((Activity)mContext).findViewById(R.id.dialog_normal));
        ((TextView) dialog.findViewById(R.id.title)).setText(title);
        ((TextView) dialog.findViewById(R.id.content)).setText("");
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        View customTitle = inflater.inflate(R.layout.dialog_layout_title, null);
        customTitle.setBackground(null);
        ((TextView) customTitle.findViewById(R.id.title)).setText(title);
        builder.setCustomTitle(customTitle);

        builder.setSingleChoiceItems(contentList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "single choice items click = "+which);
                index = which;
            }
        });

        builder.setPositiveButton(confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "confirm btn click : "+contentList[index]);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "confirm btn click : ");
                dialog.dismiss();
            }
        });

        final Dialog dialogA = builder.setView(dialog).create();
        dialog.findViewById(R.id.title).setVisibility(View.GONE);
        dialog.findViewById(R.id.content).setVisibility(View.GONE);
        dialog.findViewById(R.id.bottom).setVisibility(View.GONE);

        dialogA.show();
        return dialogA;
    }

}
