package com.example.azuredragon.http.impl;

import android.support.annotation.NonNull;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.IView;


public abstract class BasePresenterImpl<T extends IView> implements IPresenter {
    protected T mView;

    @Override
    public void attachView(@NonNull IView iView) {
        mView = (T) iView;
    }
}
