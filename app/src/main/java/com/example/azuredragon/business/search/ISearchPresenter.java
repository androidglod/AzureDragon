package com.example.azuredragon.business.search;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
import com.example.azuredragon.IPresenter;
import com.example.azuredragon.http.bean.SearchBookBean;

public interface ISearchPresenter extends IPresenter {

    Boolean getHasSearch();

    void setHasSearch(Boolean hasSearch);

    void insertSearchHistory();

    void querySearchHistory();

    void cleanSearchHistory();

    int getPage();

    void initPage();

    void toSearchBooks(String key, Boolean fromError);

    void addBookToShelf(final SearchBookBean searchBookBean);

    Boolean getInput();

    void setInput(Boolean input);
}
