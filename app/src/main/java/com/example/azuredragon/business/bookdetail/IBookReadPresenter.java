package com.example.azuredragon.business.bookdetail;

import android.app.Activity;

import com.example.azuredragon.BookContentView;
import com.example.azuredragon.IPresenter;
import com.example.azuredragon.http.bean.BookShelfBean;


public interface IBookReadPresenter extends IPresenter {

    int getOpen_from();

    BookShelfBean getBookShelf();

    void initContent();

    void loadContent(BookContentView bookContentView, long bookTag, final int chapterIndex, final int page);

    void updateProgress(int chapterIndex, int pageIndex);

    void saveProgress();

    String getChapterTitle(int chapterIndex);

    void setPageLineCount(int pageLineCount);

    void addToShelf(final ReadBookPresenterImpl.OnAddListner addListner);

    Boolean getAdd();

    void initData(Activity activity);

    void openBookFromOther(Activity activity);
}
