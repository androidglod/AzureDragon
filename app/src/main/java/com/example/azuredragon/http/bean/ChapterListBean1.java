package com.example.azuredragon.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description: 章节列表
 */

public class ChapterListBean1  {

    private List<ChaptersBean> chaptersList;


    protected ChapterListBean1(Parcel in) {
    }


    public List<ChaptersBean> getChaptersList() {
        return chaptersList;
    }

    public void setChaptersList(List<ChaptersBean> chaptersList) {
        this.chaptersList = chaptersList;
    }


}
