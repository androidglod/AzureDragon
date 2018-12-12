package com.example.azuredragon.http.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:读取cookie
 */
public abstract class SimpleObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
