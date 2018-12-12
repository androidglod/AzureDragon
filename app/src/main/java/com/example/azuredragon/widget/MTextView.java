package com.example.azuredragon.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public class MTextView extends android.support.v7.widget.AppCompatTextView {

    public MTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getTextColors().getDefaultColor());
        Layout layout = new StaticLayout(getText(), paint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
        layout.draw(canvas);
    }
}