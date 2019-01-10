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

public class ChapterListBean1 implements Parcelable,Cloneable {

    private int total;
    private int pageNo;
    private List<ChaptersBean> chaptersList;
    private String noteUrl; //对应BookInfoBean noteUrl;

    private int durChapterIndex;  //当前章节数
    private String durChapterUrl;  //当前章节对应的文章地址

    private String durChapterName;  //当前章节名称

    private String tag;

    private Boolean hasCache = false;
    private BookContentBean bookContentBean = new BookContentBean();

    protected ChapterListBean1(Parcel in) {
        noteUrl = in.readString();
        durChapterIndex = in.readInt();
        durChapterUrl = in.readString();
        durChapterName = in.readString();
        tag = in.readString();
        bookContentBean = in.readParcelable(BookContentBean.class.getClassLoader());
        hasCache = in.readByte() != 0;
    }

    @Generated(hash = 1225922702)
    public ChapterListBean1(String noteUrl, int durChapterIndex, String durChapterUrl,
                            String durChapterName, String tag, Boolean hasCache) {
        this.noteUrl = noteUrl;
        this.durChapterIndex = durChapterIndex;
        this.durChapterUrl = durChapterUrl;
        this.durChapterName = durChapterName;
        this.tag = tag;
        this.hasCache = hasCache;
    }

    @Generated(hash = 1096893365)
    public ChapterListBean1() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteUrl);
        dest.writeInt(durChapterIndex);
        dest.writeString(durChapterUrl);
        dest.writeString(durChapterName);
        dest.writeString(tag);
        dest.writeParcelable(bookContentBean, flags);
        dest.writeByte((byte)(hasCache?1:0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public BookContentBean getBookContentBean() {
        return bookContentBean;
    }

    public void setBookContentBean(BookContentBean bookContentBean) {
        this.bookContentBean = bookContentBean;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<ChaptersBean> getChaptersList() {
        return chaptersList;
    }

    public void setChaptersList(List<ChaptersBean> chaptersList) {
        this.chaptersList = chaptersList;
    }

    public Boolean getHasCache() {
        return this.hasCache;
    }

    public void setHasCache(Boolean hasCache) {
        this.hasCache = hasCache;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDurChapterName() {
        return this.durChapterName;
    }

    public void setDurChapterName(String durChapterName) {
        this.durChapterName = durChapterName;
    }

    public String getDurChapterUrl() {
        return this.durChapterUrl;
    }

    public void setDurChapterUrl(String durChapterUrl) {
        this.durChapterUrl = durChapterUrl;
    }

    public int getDurChapterIndex() {
        return this.durChapterIndex;
    }

    public void setDurChapterIndex(int durChapterIndex) {
        this.durChapterIndex = durChapterIndex;
    }

    public String getNoteUrl() {
        return this.noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    @Transient
    public static final Creator<ChapterListBean1> CREATOR = new Creator<ChapterListBean1>() {
        @Override
        public ChapterListBean1 createFromParcel(Parcel in) {
            return new ChapterListBean1(in);
        }

        @Override
        public ChapterListBean1[] newArray(int size) {
            return new ChapterListBean1[size];
        }
    };

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ChapterListBean1 chapterListBean = (ChapterListBean1) super.clone();
        chapterListBean.noteUrl = noteUrl;
        chapterListBean.durChapterUrl = durChapterUrl;
        chapterListBean.durChapterName = durChapterName;
        chapterListBean.tag = tag;
        chapterListBean.hasCache = hasCache;
        chapterListBean.bookContentBean = new BookContentBean();
        return chapterListBean;
    }
}
