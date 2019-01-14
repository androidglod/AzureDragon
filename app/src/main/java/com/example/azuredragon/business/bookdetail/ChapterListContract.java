package com.example.azuredragon.business.bookdetail;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.ChaptersBean;

import java.util.HashMap;
import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:章节列表
 */

public interface ChapterListContract {
    interface View extends BaseView<presenter> {
        //获取章节列表成功
        void success(List<ChaptersBean> library);
        //获取章节列表失败
        void fail(String content);
    }

    interface presenter extends BasePresenter {
        //获取章节列表
        void getChapterList(HashMap map);

    }


}
