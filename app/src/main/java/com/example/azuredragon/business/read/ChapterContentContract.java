package com.example.azuredragon.business.read;

import com.example.azuredragon.business.bookdetail.ChapterListContract;
import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.BookChapterContentBean;
import com.example.azuredragon.http.bean.ChapterListBean;

import java.util.HashMap;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:章节列表
 */

public interface ChapterContentContract {
    interface View extends BaseView<presenter> {
        //获取章节内容成功
        void success(BookChapterContentBean library);
        //获取章节内容失败
        void fail(String content);
    }

    interface presenter extends BasePresenter {
        //获取章节内容
        void getChapterContent(HashMap map);

    }


}
