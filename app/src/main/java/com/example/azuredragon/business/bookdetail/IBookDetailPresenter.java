package com.example.azuredragon.business.bookdetail;


import com.example.azuredragon.IPresenter;
import com.example.azuredragon.http.bean.BookDetailBean;
import com.example.azuredragon.http.bean.BookShelfBean;

public interface IBookDetailPresenter extends IPresenter {

    int getOpenfrom();

    BookDetailBean getSearchBook();

    BookShelfBean getBookShelf();

    Boolean getInBookShelf();

    void getBookShelfInfo();

    void addToBookShelf();

    void removeFromBookShelf();
}
