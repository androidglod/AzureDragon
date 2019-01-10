package com.example.azuredragon.http.bean;

import org.greenrobot.greendao.annotation.Id;

public class ChaptersBean {



    private int chapterId;  //文章id
    private String worksName;  //作品名称
    private String chapterTitle;  //文章标题

    private String fictionTypeName;  //作品类型
    private String chargeStatus;  // 收费状态 1、免费 2、收费
    private int payGold;  //支付书币

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

    public String getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(String chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public int getPayGold() {
        return payGold;
    }

    public void setPayGold(int payGold) {
        this.payGold = payGold;
    }
}
