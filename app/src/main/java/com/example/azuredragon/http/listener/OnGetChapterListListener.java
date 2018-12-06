//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.example.azuredragon.http.listener;


import com.example.azuredragon.http.bean.BookShelfBean;

public interface OnGetChapterListListener {
    public void success(BookShelfBean bookShelfBean);
    public void error();
}
