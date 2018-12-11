package com.example.azuredragon.http.bean;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  书城 书籍分类推荐列表
 */
public class LocBookShelfBean {
    private Boolean isNew;
    private BookShelfBean bookShelfBean;

    public LocBookShelfBean(Boolean isNew, BookShelfBean bookShelfBean){
        this.isNew = isNew;
        this.bookShelfBean = bookShelfBean;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public BookShelfBean getBookShelfBean() {
        return bookShelfBean;
    }

    public void setBookShelfBean(BookShelfBean bookShelfBean) {
        this.bookShelfBean = bookShelfBean;
    }
}
