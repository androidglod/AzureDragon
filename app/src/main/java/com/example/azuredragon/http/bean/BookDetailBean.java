package com.example.azuredragon.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  书城 书籍分类推荐列表
 */
public class BookDetailBean implements Parcelable {
    private long worksId;
    private String worksName;
    private String worksCoverPic;
    private int affiliatedChannel;
    private String fictionTypeName;
    private String fictionTypeId;
    private String writer;
    private int writingState;
    private String worksDes;
    private int totalWordsNumber;
    private int auditStatus;
    private Boolean isAdd = false;

    public String getFictionTypeId() {
        return fictionTypeId;
    }

    public void setFictionTypeId(String fictionTypeId) {
        this.fictionTypeId = fictionTypeId;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public long getWorksId() {
        return worksId;
    }

    public void setWorksId(long worksId) {
        this.worksId = worksId;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getWorksCoverPic() {
        return worksCoverPic;
    }

    public void setWorksCoverPic(String worksCoverPic) {
        this.worksCoverPic = worksCoverPic;
    }

    public int getAffiliatedChannel() {
        return affiliatedChannel;
    }

    public void setAffiliatedChannel(int affiliatedChannel) {
        this.affiliatedChannel = affiliatedChannel;
    }

    public String getFictionTypeName() {
        return fictionTypeName;
    }

    public void setFictionTypeName(String fictionTypeName) {
        this.fictionTypeName = fictionTypeName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getWritingState() {
        return writingState;
    }

    public void setWritingState(int writingState) {
        this.writingState = writingState;
    }

    public String getWorksDes() {
        return worksDes;
    }

    public void setWorksDes(String worksDes) {
        this.worksDes = worksDes;
    }

    public int getTotalWordsNumber() {
        return totalWordsNumber;
    }

    public void setTotalWordsNumber(int totalWordsNumber) {
        this.totalWordsNumber = totalWordsNumber;
    }

    public Boolean getAdd() {
        return isAdd;
    }

    public void setAdd(Boolean add) {
        isAdd = add;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookDetailBean> CREATOR = new Creator<BookDetailBean>() {
        @Override
        public BookDetailBean createFromParcel(Parcel in) {
            return new BookDetailBean(in);
        }

        @Override
        public BookDetailBean[] newArray(int size) {
            return new BookDetailBean[size];
        }
    };

    protected BookDetailBean(Parcel in) {

        worksId = in.readLong();
        worksName = in.readString();
        worksCoverPic = in.readString();
        affiliatedChannel = in.readInt();
        fictionTypeName = in.readString();
        fictionTypeId = in.readString();
        writer = in.readString();
        writingState = in.readInt();
        worksDes = in.readString();
        totalWordsNumber = in.readInt();
        auditStatus = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(worksId);
        dest.writeString(worksName);
        dest.writeString(worksCoverPic);
        dest.writeInt(affiliatedChannel);
        dest.writeString(fictionTypeName);
        dest.writeString(fictionTypeId);
        dest.writeString(writer);
        dest.writeInt(writingState);
        dest.writeString(worksDes);
        dest.writeInt(totalWordsNumber);
        dest.writeInt(auditStatus);


    }
}