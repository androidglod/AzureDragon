//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.example.azuredragon.business.bookdetail;


import com.example.azuredragon.IPresenter;
import com.example.azuredragon.http.bean.BookShelfBean;
import com.example.azuredragon.http.bean.SearchBookBean;

public interface IBookDetailPresenter extends IPresenter {

    int getOpenfrom();

    SearchBookBean getSearchBook();

    BookShelfBean getBookShelf();

    Boolean getInBookShelf();

    void getBookShelfInfo();

    void addToBookShelf();

    void removeFromBookShelf();
}
