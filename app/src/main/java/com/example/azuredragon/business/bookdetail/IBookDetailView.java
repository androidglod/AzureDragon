//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.example.azuredragon.business.bookdetail;


import com.example.azuredragon.IView;

public interface IBookDetailView extends IView {
    /**
     * 更新书籍详情UI
     */
    void updateView();

    /**
     * 数据获取失败
     */
    void getBookShelfError();
}
