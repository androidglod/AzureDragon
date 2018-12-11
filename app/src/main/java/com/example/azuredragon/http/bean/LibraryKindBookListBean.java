package com.example.azuredragon.http.bean;

import java.util.List;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  书城 书籍分类推荐列表
 */

public class LibraryKindBookListBean {
    private String kindName;
    private String kindUrl;
    private List<SearchBookBean> books;

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public List<SearchBookBean> getBooks() {
        return books;
    }

    public void setBooks(List<SearchBookBean> books) {
        this.books = books;
    }

    public String getKindUrl() {
        return kindUrl;
    }

    public void setKindUrl(String kindUrl) {
        this.kindUrl = kindUrl;
    }
}
