package com.example.azuredragon.business.seting;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.LoginBean;

import java.util.HashMap;


/**
 * @author: chz
 * @date: 2018/11/25
 * @description:反馈
 */

public interface FeedbackContract {
    interface View extends BaseView<presenter> {
        void success( );  //反馈成功
        void fail( );  //反馈失败

    }

    interface presenter extends BasePresenter {

        void goFeedback(HashMap map); //注册

    }
}
