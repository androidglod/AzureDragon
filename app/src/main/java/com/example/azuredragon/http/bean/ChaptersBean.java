package com.example.azuredragon.http.bean;

import org.greenrobot.greendao.annotation.Id;

public class ChaptersBean {



    private int chapterId;  //文章id
    private int worksId;  //文章id
    private int reelId;  //文章id
    private String worksName;  //作品名称
    private String chapterTitle;  //文章标题
    private String fictionTypeName;  //作品类型
    private double bookMoney;  //文章id
    private String state;  //文章id
    private String chargeStatus;  // 收费状态 1、免费 2、收费
    private long createDate;  //文章id
    private int writerId;  //文章id
    private String writer;  //文章id

    private String content;  //文章id
    private String authorSaidContent;  //文章id
    private int clickNumber;  //文章id
    private int collectNumber;  //文章id
    private int subscriptionNumber;  //文章id

    private int payGold;  //支付书币

    public int getWorksId() {
        return worksId;
    }

    public void setWorksId(int worksId) {
        this.worksId = worksId;
    }

    public void setBookMoney(double bookMoney) {
        this.bookMoney = bookMoney;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public long getReelId() {
        return reelId;
    }

    public void setReelId(int reelId) {
        this.reelId = reelId;
    }

    public double getBookMoney() {
        return bookMoney;
    }

    public void setBookMoney(int bookMoney) {
        this.bookMoney = bookMoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }




    public long getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorSaidContent() {
        return authorSaidContent;
    }

    public void setAuthorSaidContent(String authorSaidContent) {
        this.authorSaidContent = authorSaidContent;
    }

    public long getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(int clickNumber) {
        this.clickNumber = clickNumber;
    }

    public long getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    public long getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(int subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
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
