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

@Entity
public class ChapterListBean implements Parcelable,Cloneable {

    private int total;
    private int pageNo;

    private String noteUrl; //对应BookInfoBean noteUrl;

    private int durChapterIndex;  //当前章节数
    @Id
    private String durChapterUrl;  //当前章节对应的文章地址

    private String durChapterName;  //当前章节名称
    private int durChapterId;  //当前章节名称
    private String tag;

    private Boolean hasCache = false;
    @Transient
    private BookContentBean bookContentBean = new BookContentBean();

    protected ChapterListBean(Parcel in) {
        noteUrl = in.readString();
        durChapterIndex = in.readInt();
        durChapterUrl = in.readString();
        durChapterName = in.readString();
        durChapterId = in.readInt();
        tag = in.readString();
        bookContentBean = in.readParcelable(BookContentBean.class.getClassLoader());
        hasCache = in.readByte() != 0;
    }




    @Generated(hash = 306183219)
    public ChapterListBean(int total, int pageNo, String noteUrl, int durChapterIndex,
            String durChapterUrl, String durChapterName, int durChapterId, String tag,
            Boolean hasCache) {
        this.total = total;
        this.pageNo = pageNo;
        this.noteUrl = noteUrl;
        this.durChapterIndex = durChapterIndex;
        this.durChapterUrl = durChapterUrl;
        this.durChapterName = durChapterName;
        this.durChapterId = durChapterId;
        this.tag = tag;
        this.hasCache = hasCache;
    }




    @Generated(hash = 1096893365)
    public ChapterListBean() {
    }




    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteUrl);
        dest.writeInt(durChapterIndex);
        dest.writeString(durChapterUrl);
        dest.writeString(durChapterName);
        dest.writeInt(durChapterId);
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

    public int getDurChapterId() {
        return durChapterId;
    }

    public void setDurChapterId(int durChapterId) {
        this.durChapterId = durChapterId;
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
    public static final Creator<ChapterListBean> CREATOR = new Creator<ChapterListBean>() {
        @Override
        public ChapterListBean createFromParcel(Parcel in) {
            return new ChapterListBean(in);
        }

        @Override
        public ChapterListBean[] newArray(int size) {
            return new ChapterListBean[size];
        }
    };

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ChapterListBean chapterListBean = (ChapterListBean) super.clone();
        chapterListBean.noteUrl = noteUrl;
        chapterListBean.durChapterUrl = durChapterUrl;
        chapterListBean.durChapterName = durChapterName;
        chapterListBean.durChapterId = durChapterId;
        chapterListBean.tag = tag;
        chapterListBean.hasCache = hasCache;
        chapterListBean.bookContentBean = new BookContentBean();
        return chapterListBean;
    }
}
