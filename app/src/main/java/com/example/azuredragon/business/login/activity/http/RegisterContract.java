package com.example.azuredragon.business.login.activity.http;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.LoginBean;

import java.util.HashMap;


/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public interface RegisterContract {
    interface View extends BaseView<presenter> {
        void success(LoginBean mLoginBean);  //注册成功
        void fail(String content);  //注册失败

        void checkPhonesuccess(String content);  //验证成功
        void checkPhonefail(String content);  //验证失败
    }

    interface presenter extends BasePresenter {

        void goRegister(HashMap map); //注册

        void checkPhone(HashMap map); //验证手机号
    }
}
