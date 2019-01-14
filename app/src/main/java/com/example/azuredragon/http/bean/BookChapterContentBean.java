package com.example.azuredragon.http.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  章节内容
 */
public class BookChapterContentBean implements Serializable {

    private String   data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}