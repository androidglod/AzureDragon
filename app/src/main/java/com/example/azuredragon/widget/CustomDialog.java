package com.example.azuredragon.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


public class CustomDialog extends Dialog {
    private Context context;
    private int resId;

    public CustomDialog(Context context, int resLayout) {
        this(context, 0, 0);
    }

    public CustomDialog(Context context, int themeResId, int resLayout) {
        super(context, themeResId);
        this.context = context;
        this.resId = resLayout;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.resId);
    }
}