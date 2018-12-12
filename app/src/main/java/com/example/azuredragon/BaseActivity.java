package com.example.azuredragon;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:Activity管理器,管理项目中Activity的状态
 */
public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IView {
    public final static String start_share_ele= "start_with_share_ele";
    protected Bundle savedInstanceState;
    protected T mPresenter;
    private Boolean startShareAnim = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getIntent()!=null){
            startShareAnim = getIntent().getBooleanExtra(start_share_ele,false);
        }
        ActivityManager.getInstance().add(this);
        initSDK();
        onCreateActivity();
        ButterKnife.bind(this);
        mPresenter = initInjector();
        attachView();
        bindView();
        initData();
        bindEvent();
        firstRequest();

    }

    /**
     * 首次逻辑操作
     */
    protected void firstRequest() {

    }

    /**
     * 事件触发绑定
     */
    protected void bindEvent() {

    }

    /**
     * 控件绑定
     */
    protected void bindView() {

    }

    /**
     * P层绑定V层
     */
    private void attachView() {
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    /**
     * P层解绑V层
     */
    private void detachView() {
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    /**
     * SDK初始化
     */
    protected void initSDK() {

    }

    /**
     * P层绑定   若无则返回null;
     *
     * @return
     */
    protected abstract T initInjector();

    /**
     * 布局载入  setContentView()
     */
    protected abstract void onCreateActivity();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
        ActivityManager.getInstance().remove(this);
    }

    ////////////////////////////////启动Activity转场动画/////////////////////////////////////////////

    protected void startActivityForResultByAnim(Intent intent, int requestCode, int animIn, int animExit) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(animIn, animExit);
    }

    protected void startActivityByAnim(Intent intent, int animIn, int animExit) {
        startActivity(intent);
        overridePendingTransition(animIn, animExit);
    }

    protected void startActivityForResultByAnim(Intent intent, int requestCode, @NonNull View view, @NonNull String transitionName, int animIn, int animExit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivityForResult(intent, requestCode, ActivityOptions.makeSceneTransitionAnimation(this, view, transitionName).toBundle());
        } else {
            startActivityForResultByAnim(intent, requestCode, animIn, animExit);
        }
    }

    protected void startActivityByAnim(Intent intent, @NonNull View view, @NonNull String transitionName, int animIn, int animExit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.putExtra(start_share_ele,true);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, view, transitionName).toBundle());
        } else {
            startActivityByAnim(intent, animIn, animExit);
        }
    }

    public Context getContext(){
        return this;
    }

    public Boolean getStart_share_ele() {
        return startShareAnim;
    }
}