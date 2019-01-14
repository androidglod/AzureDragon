package com.example.azuredragon.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Id;

public class ChaptersBean implements  Parcelable  {


    private int chapterId;  //文章id
//    private int worksId;  //文章id
//    private int reelId;  //文章id
    private String worksName;  //作品名称
    private String chapterTitle;  //文章标题
    private String fictionTypeName;  //作品类型
//    private double bookMoney;  //文章id
//    private String state;  //文章id
    private int chargeStatus;  // 收费状态 1、免费 2、收费
//    private long createDate;  //文章id
//    private int writerId;  //文章id
//    private String writer;  //文章id
//
//    private String content;  //文章id
//    private String authorSaidContent;  //文章id
//    private int clickNumber;  //文章id
//    private int collectNumber;  //文章id
//    private int subscriptionNumber;  //文章id

    private int payGold;  //支付书币

    protected ChaptersBean(Parcel in) {
        chapterId = in.readInt();
        worksName = in.readString();
        chapterTitle = in.readString();
        fictionTypeName = in.readString();
        chargeStatus = in.readInt();
        payGold = in.readInt();
    }

    public static final Creator<ChaptersBean> CREATOR = new Creator<ChaptersBean>() {
        @Override
        public ChaptersBean createFromParcel(Parcel in) {
            return new ChaptersBean(in);
        }

        @Override
        public ChaptersBean[] newArray(int size) {
            return new ChaptersBean[size];
        }
    };

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getFictionTypeName() {
        return fictionTypeName;
    }

    public void setFictionTypeName(String fictionTypeName) {
        this.fictionTypeName = fictionTypeName;
    }

    public int getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(int chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public int getPayGold() {
        return payGold;
    }

    public void setPayGold(int payGold) {
        this.payGold = payGold;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(chapterId);
        dest.writeString(worksName);
        dest.writeString(chapterTitle);
        dest.writeString(fictionTypeName);
        dest.writeInt(chargeStatus);
        dest.writeInt(payGold);
    }
}
