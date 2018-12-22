package com.example.azuredragon.http.bean;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  书城 书籍分类推荐列表
 */
public class BookDetailBean {
    private long worksId;
    private String worksName;
    private String worksCoverPic;
    private int affiliatedChannel;
    private String fictionTypeName;
    private String writer;
    private int writingState;
    private String worksDes;
    private int totalWordsNumber;

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
}