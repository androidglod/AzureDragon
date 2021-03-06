package com.example.azuredragon.business.login.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.business.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description：欢迎页
 */
public class WelcomeActivity extends MBaseActivity {

    @BindView(R.id.iv_welcome_intro)
    ImageView mIvWelcomeIntro;

    @BindView(R.id.tv_welcome_intro)
    TextView mTvWelcomeIntro;


    private ValueAnimator welAnimator;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
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
