package com.example.azuredragon.http.bean;

import java.util.List;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  书城整体Data bean
 */
public class LibraryBean {
    private List<LibraryNewBookBean> libraryNewBooks;
    private List<LibraryKindBookListBean> kindBooks;

    public List<LibraryNewBookBean> getLibraryNewBooks() {
        return libraryNewBooks;
    }

    public void setLibraryNewBooks(List<LibraryNewBookBean> libraryNewBooks) {
        this.libraryNewBooks = libraryNewBooks;
    }

    public List<LibraryKindBookListBean> getKindBooks() {
        return kindBooks;
    }

    public void setKindBooks(List<LibraryKindBookListBean> kindBooks) {
        this.kindBooks = kindBooks;
    }
}
