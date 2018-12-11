package com.example.azuredragon.http.bean;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  阅读bean
 */
import java.util.List;

public class ReadBookContentBean {
    private List<BookContentBean> bookContentList;
    private int pageIndex;

    public ReadBookContentBean(List<BookContentBean> bookContentList, int pageIndex){
        this.bookContentList =  bookContentList;
        this.pageIndex = pageIndex;
    }

    public List<BookContentBean> getBookContentList() {
        return bookContentList;
    }

    public void setBookContentList(List<BookContentBean> bookContentList) {
        this.bookContentList = bookContentList;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
