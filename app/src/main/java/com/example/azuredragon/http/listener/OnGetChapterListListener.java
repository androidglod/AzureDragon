package com.example.azuredragon.http.listener;


import com.example.azuredragon.http.bean.BookShelfBean;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */
public interface OnGetChapterListListener {
    public void success(BookShelfBean bookShelfBean);
    public void error();
}
