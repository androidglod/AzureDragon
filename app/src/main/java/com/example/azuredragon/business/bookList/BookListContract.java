package com.example.azuredragon.business.bookList;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.BookListBean;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:书籍列表
 */

public interface BookListContract {
    LinkedHashMap<String,String> kinds = new LinkedHashMap<>();
    interface View extends BaseView<presenter> {
        //获取书籍列表成功
        void success(BookListBean library);
        //获取书籍列表失败
        void fail(String content);
        LinkedHashMap<String,String> getLinked();
    }

    interface presenter extends BasePresenter {
        //获取书籍列表
        void getBookList(HashMap map);

    }


}
