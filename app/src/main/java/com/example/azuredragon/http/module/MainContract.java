package com.example.azuredragon.http.module;

import android.graphics.Bitmap;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;


/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public interface MainContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void setCode(Bitmap value);  //设置验证码
    }

    interface presenter extends BasePresenter {
        void getCode(); //获取验证码

        void userLogin(String user, String pwd, String code); //登录

        void getBannerData();  //banner

        void getLiveData();   //资讯信息
    }
}
