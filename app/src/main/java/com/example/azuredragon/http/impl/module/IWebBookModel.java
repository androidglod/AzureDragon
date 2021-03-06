package com.example.azuredragon.http.impl.module;


import com.example.azuredragon.http.bean.BookContentBean;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.http.bean.SearchBookBean;
import com.example.azuredragon.http.listener.OnGetChapterListListener;

import java.util.List;

import io.reactivex.Observable;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public interface IWebBookModel {
    /**
     * 网络请求并解析书籍信息
     */
    Observable<BookShelfBean> getBookInfo(final BookShelfBean bookShelfBean);

    /**
     * 网络解析图书目录
     */
    void getChapterList(final BookShelfBean bookShelfBean, OnGetChapterListListener getChapterListListener);

    /**
     * 章节缓存
     */
    Observable<BookContentBean> getBookContent(final String durChapterUrl, final int durChapterIndex, String tag);

    /**
     * 其他站点资源整合搜索
     */
    Observable<List<SearchBookBean>> searchOtherBook(String content, int page, String tag);
}
