package com.example.azuredragon.http.contract;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;

import java.util.HashMap;


/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public interface LoginContract {
    interface View extends BaseView<presenter> {
        void success(String content);  //登录成功
        void fail(String content);  //登录失败
    }

    interface presenter extends BasePresenter {

        void goLogin(HashMap map); //登录


    }
}
