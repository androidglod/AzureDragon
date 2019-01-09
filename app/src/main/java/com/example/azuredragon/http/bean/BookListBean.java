package com.example.azuredragon.http.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  书城 书籍分类推荐列表
 */
public class BookListBean implements Serializable {
    List<BookDetailBean> xddsList;
    List<BookDetailBean> lsjsList;
    List<BookDetailBean> gdyqList;
    List<BookDetailBean> menWorkList;
    List<BookDetailBean> mxtxList;
    List<BookDetailBean> ndsnList;
    List<BookDetailBean> xdyqList;
    List<BookDetailBean> erCiYuanWorkList;
    List<BookDetailBean> xjqhList;
    List<BookDetailBean> xhyjList;
    List<BookDetailBean> womenWorkList;
    List<BookDetailBean> xylyList;
    List<BookDetailBean> cazpList;
    List<BookTypeListBean> fictionTypeList;

    public List<BookDetailBean> getXddsList() {
        return xddsList;
    }

    public void setXddsList(List<BookDetailBean> xddsList) {
        this.xddsList = xddsList;
    }

    public List<BookDetailBean> getLsjsList() {
        return lsjsList;
    }

    public void setLsjsList(List<BookDetailBean> lsjsList) {
        this.lsjsList = lsjsList;
    }

    public List<BookDetailBean> getGdyqList() {
        return gdyqList;
    }

    public void setGdyqList(List<BookDetailBean> gdyqList) {
        this.gdyqList = gdyqList;
    }

    public List<BookDetailBean> getMenWorkList() {
        return menWorkList;
    }

    public void setMenWorkList(List<BookDetailBean> menWorkList) {
        this.menWorkList = menWorkList;
    }

    public List<BookDetailBean> getMxtxList() {
        return mxtxList;
    }

    public void setMxtxList(List<BookDetailBean> mxtxList) {
        this.mxtxList = mxtxList;
    }

    public List<BookDetailBean> getNdsnList() {
        return ndsnList;
    }

    public void setNdsnList(List<BookDetailBean> ndsnList) {
        this.ndsnList = ndsnList;
    }

    public List<BookDetailBean> getXdyqList() {
        return xdyqList;
    }

    public void setXdyqList(List<BookDetailBean> xdyqList) {
        this.xdyqList = xdyqList;
    }

    public List<BookDetailBean> getErCiYuanWorkList() {
        return erCiYuanWorkList;
    }

    public void setErCiYuanWorkList(List<BookDetailBean> erCiYuanWorkList) {
        this.erCiYuanWorkList = erCiYuanWorkList;
    }

    public List<BookDetailBean> getXjqhList() {
        return xjqhList;
    }

    public void setXjqhList(List<BookDetailBean> xjqhList) {
        this.xjqhList = xjqhList;
    }

    public List<BookDetailBean> getXhyjList() {
        return xhyjList;
    }

    public void setXhyjList(List<BookDetailBean> xhyjList) {
        this.xhyjList = xhyjList;
    }

    public List<BookDetailBean> getWomenWorkList() {
        return womenWorkList;
    }

    public void setWomenWorkList(List<BookDetailBean> womenWorkList) {
        this.womenWorkList = womenWorkList;
    }

    public List<BookDetailBean> getXylyList() {
        return xylyList;
    }

    public void setXylyList(List<BookDetailBean> xylyList) {
        this.xylyList = xylyList;
    }

    public List<BookDetailBean> getCazpList() {
        return cazpList;
    }

    public void setCazpList(List<BookDetailBean> cazpList) {
        this.cazpList = cazpList;
    }

    public List<BookTypeListBean> getFictionTypeList() {
        return fictionTypeList;
    }

    public void setFictionTypeList(List<BookTypeListBean> fictionTypeList) {
        this.fictionTypeList = fictionTypeList;
    }
}