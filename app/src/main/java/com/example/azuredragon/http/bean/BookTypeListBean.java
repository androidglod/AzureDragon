package com.example.azuredragon.http.bean;

import java.io.Serializable;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  书城 书籍分类
 */
public class BookTypeListBean implements Serializable {

    long fictionTypeId;
    String fictionTypeName;

    public long getFictionTypeId() {
        return fictionTypeId;
    }

    public void setFictionTypeId(long fictionTypeId) {
        this.fictionTypeId = fictionTypeId;
    }

    public String getFictionTypeName() {
        return fictionTypeName;
    }

    public void setFictionTypeName(String fictionTypeName) {
        this.fictionTypeName = fictionTypeName;
    }
}