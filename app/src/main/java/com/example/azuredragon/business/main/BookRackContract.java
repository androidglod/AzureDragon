package com.example.azuredragon.business.main;

import com.example.azuredragon.http.base.BasePresenter;
import com.example.azuredragon.http.base.BaseView;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.http.bean.LibraryBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:书籍列表
 */

public interface BookRackContract {
    LinkedHashMap<String,String> kinds = new LinkedHashMap<>();
    interface View extends BaseView<presenter> {
        //获取书籍列表成功
        void success(LibraryBean library);
        //获取书籍列表失败
        void fail(String content);
        LinkedHashMap<String,String> getLinked();

        /**
         * 刷新书架书籍小说信息 更新UI
         * @param bookShelfBeanList
         */
        void refreshBookShelf(List<BookShelfBean> bookShelfBeanList);

        /**
         * 执行刷新书架小说信息
         */
        void activityRefreshView();

        /**
         * 刷新完成
         */
        void refreshFinish();

        /**
         * 刷新错误
         * @param error
         */
        void refreshError(String error);

        /**
         * 刷新书籍  UI进度修改
         */
        void refreshRecyclerViewItemAdd();

        /**
         * 设置刷新进度条最大值
         * @param x
         */
        void setRecyclerMaxProgress(int x);
    }

    interface presenter extends BasePresenter {
        //获取书籍列表
        void getBookList(final Boolean needRefresh);

    }


}
