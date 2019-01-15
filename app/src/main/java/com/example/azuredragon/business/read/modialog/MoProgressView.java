package com.example.azuredragon.business.read.modialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azuredragon.R;
import com.victor.loading.rotate.RotateLoading;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:书籍列表
 */
public class MoProgressView extends LinearLayout {
    private Context context;

    public MoProgressView(Context context) {
        this(context, null);
    }

    public MoProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(VERTICAL);
    }

    //转圈的载入
    public void showLoading(String text) {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.moprogress_dialog_loading, this, true);
        TextView msgTv = (TextView) findViewById(R.id.msg_tv);
        if (text != null && text.length() > 0) {
            msgTv.setText(text);
        }
        RotateLoading rlLoading = (RotateLoading) findViewById(R.id.rl_loading);
        rlLoading.start();
    }

    //单个按钮的信息提示框
    public void showInfo(String msg, final OnClickListener listener) {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.moprogress_dialog_infor, this, true);
        TextView msgTv = (TextView) findViewById(R.id.msg_tv);
        msgTv.setText(msg);
        TextView tvClose = (TextView) findViewById(R.id.tv_close);
        tvClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
    }

    //单个按钮的信息提示框
    public void showInfo(String msg, String btnText, final OnClickListener listener) {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.moprogress_dialog_infor, this, true);
        TextView msgTv = (TextView) findViewById(R.id.msg_tv);
        msgTv.setText(msg);
        TextView tvClose = (TextView) findViewById(R.id.tv_close);
        tvClose.setText(btnText);
        tvClose.setOnClickListener(listener);
    }
    //////////////////////两个不同等级的按钮//////////////////////
    public void showTwoButton(String msg, String b_f, OnClickListener c_f, String b_s, OnClickListener c_s) {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.moprogress_dialog_two, this, true);
        TextView tvMsg = (TextView) findViewById(R.id.tv_msg);
        TextView tvCancel = (TextView) findViewById(R.id.tv_cancel);
        TextView tvDone = (TextView) findViewById(R.id.tv_done);
        tvMsg.setText(msg);
        tvCancel.setText(b_f);
        tvCancel.setOnClickListener(c_f);
        tvDone.setText(b_s);
        tvDone.setOnClickListener(c_s);
    }


}