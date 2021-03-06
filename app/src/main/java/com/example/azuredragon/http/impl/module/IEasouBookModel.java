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
public interface IEasouBookModel {
    /**
     * 搜索书籍
     */
    Observable<List<SearchBookBean>> searchBook(String content, int page, int rankKind);

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
    Observable<BookContentBean> getBookContent(final String durChapterUrl, final int durChapterIndex);
}
