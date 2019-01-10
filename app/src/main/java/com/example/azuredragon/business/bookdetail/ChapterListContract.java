package com.example.azuredragon.business.bookdetail;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.BookListBean;
import com.example.azuredragon.http.bean.ChapterListBean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:章节列表
 */

public interface ChapterListContract {
    interface View extends BaseView<presenter> {
        //获取章节列表成功
        void success(ChapterListBean library);
        //获取章节列表失败
        void fail(String content);
    }

    interface presenter extends BasePresenter {
        //获取章节列表
        void getChapterList(HashMap map);

    }


}
