package com.example.azuredragon.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.example.azuredragon.R;

import java.util.ArrayList;

public class DialogManager {
    private Context mContext = null;

    public DialogManager(Context context) {
        this.mContext = context;
    }

    public Dialog showConfirmDialog(String msg, String btnPositiveName, boolean isCancelable, DialogManager.DialogCallback callback) {
        return this.showConfirmDialog(this.mContext, "", msg, btnPositiveName, "", isCancelable, callback);
    }

    public Dialog showTitleAndMsgDialog(String title, String msg, String btnPositiveName, boolean isCancelable, DialogManager.DialogCallback callback) {
        return this.showConfirmDialog(this.mContext, title, msg, btnPositiveName, "", isCancelable, callback);
    }

    public Dialog showTitleAndMsgDialog(String title, String msg, String btnNegativName, String btnPositiveName, boolean isCancelable, DialogManager.DialogCallback callback) {
        return this.showConfirmDialog(this.mContext, title, msg, btnPositiveName, btnNegativName, isCancelable, callback);
    }

    public Dialog showMsgDialog(String msg, String btnPositiveName, String btnNegativName, boolean isCancelable, DialogManager.DialogCallback callback) {
        return this.showConfirmDialog(this.mContext, "", msg, btnPositiveName, btnNegativName, isCancelable, callback);
    }

    @SuppressLint("WrongConstant")
    private Dialog showConfirmDialog(Context mContext, CharSequence title, CharSequence msg, CharSequence btnPositiveName, CharSequence onNegativeName, boolean isCancelable, final DialogManager.DialogCallback callback) {
        final CustomDialog dialog = new CustomDialog(mContext, R.style.customDialog, R.layout.commwidget_comm_custom_dialog);
        dialog.setCancelable(isCancelable);
        dialog.show();
        TextView tvTitle = (TextView)dialog.findViewById(R.id.tv_dialog_title);
        if (isNotEmpty(title.toString())) {
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(8);
        }

        TextView tvMessage = (TextView)dialog.findViewById(R.id.tv_dialog_message);
        TextView button = (TextView)dialog.findViewById(R.id.btn_ok);
        TextPaint tp = button.getPaint();
        tp.setStyle(Paint.Style.FILL_AND_STROKE);
        tp.setStrokeWidth(2.0F);
        TextView cancel = (TextView)dialog.findViewById(R.id.btn_cancel);
        View line = dialog.findViewById(R.id.ll_line);
        if (isNotEmpty(onNegativeName.toString())) {
            cancel.setText(onNegativeName);
        } else {
            cancel.setVisibility(8);
            line.setVisibility(8);
        }

        tvTitle.setText(title);
        tvMessage.setText(msg);
        button.setText(btnPositiveName);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                if (callback != null) {
                    callback.onPositiveButton();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                if (callback != null) {
                    callback.onNegativeButton();
                }

            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (callback != null) {
                    callback.onDialogDismiss();
                }

            }
        });
        return dialog;
    }

    private static boolean isNotEmpty(String s) {
        return null != s && s.length() > 0 && !"".equals(s) && !"null".equals(s);
    }

    public abstract static class DialogCallback {
        public DialogCallback() {
        }

        public abstract void onPositiveButton();

        public void onNeutralButton() {
        }

        public void onNegativeButton() {
        }

        public void onGetReturnValue(ArrayList<Object> ReturnValues) {
        }

        public void onDialogDismiss() {
        }

        public void onPositiveButton(Object object) {
        }
    }
}
