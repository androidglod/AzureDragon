package com.example.azuredragon.business.BookList;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.LibraryBean;

import java.util.LinkedHashMap;


/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public interface BookListContract {
    LinkedHashMap<String,String> kinds = new LinkedHashMap<>();
    interface View extends BaseView<presenter> {
        void success(LibraryBean library);  //获取书籍列表
        void fail(String content);  //获取书籍列表
        LinkedHashMap<String,String> getLinked();
    }

    interface presenter extends BasePresenter {

        void getBookList(); //获取书籍列表

    }


}
