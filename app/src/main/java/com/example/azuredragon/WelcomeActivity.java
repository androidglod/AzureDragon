//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.example.azuredragon;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.BindView;

public class WelcomeActivity extends MBaseActivity {

    @BindView(R2.id.iv_welcome_intro)
    ImageView mIvWelcomeIntro;

    @BindView(R2.id.tv_welcome_intro)
    TextView mTvWelcomeIntro;


    private ValueAnimator welAnimator;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initData() {
        welAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(800);
        welAnimator.setStartDelay(400);
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void bindEvent() {
        welAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (Float) animation.getAnimatedValue();
                mIvWelcomeIntro.setAlpha(alpha);
                mTvWelcomeIntro.setAlpha(1f - alpha);
            }
        });
        welAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivityByAnim(new Intent(WelcomeActivity.this, MainActivity.class), android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void firstRequest() {
        welAnimator.start();
    }

}
