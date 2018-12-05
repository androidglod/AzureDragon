package com.example.azuredragon.http.contract;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;


/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public interface LoginContract {
    interface View extends BaseView<presenter> {
        void success(String content);  //获取书籍列表
        void fail(String content);  //获取书籍列表
    }

    interface presenter extends BasePresenter {

        void getBookList(); //获取书籍列表

        void getLiveData();
    }
}
