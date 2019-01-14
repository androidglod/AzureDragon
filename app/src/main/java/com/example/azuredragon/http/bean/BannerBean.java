package com.example.azuredragon.http.bean;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description: 章节列表
 */



public class BannerBean implements Serializable {
    private String adNameUrl;
    private String linkUrl;
    private String adAttUrl;
    private String adAttName;
    private String id;
    private String adLocalUrl;

    public BannerBean() {
    }

    public BannerBean(String linkUrl, String adAttUrl, String adAttName) {
        this.linkUrl = linkUrl;
        this.adAttUrl = adAttUrl;
        this.adAttName = adAttName;
    }

    public BannerBean(String adNameUrl, String linkUrl, String adAttUrl, String adAttName) {
        this.adNameUrl = adNameUrl;
        this.linkUrl = linkUrl;
        this.adAttUrl = adAttUrl;
        this.adAttName = adAttName;
    }

    public BannerBean(String adNameUrl, String linkUrl, String adAttUrl, String adAttName, String id) {
        this.adNameUrl = adNameUrl;
        this.linkUrl = linkUrl;
        this.adAttUrl = adAttUrl;
        this.adAttName = adAttName;
        this.id = id;
    }

    public String getAdNameUrl() {
        return this.adNameUrl;
    }

    public void setAdNameUrl(String adNameUrl) {
        this.adNameUrl = adNameUrl;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getAdAttUrl() {
        return this.adAttUrl;
    }

    public void setAdAttUrl(String adAttUrl) {
        this.adAttUrl = adAttUrl;
    }

    public String getAdAttName() {
        return this.adAttName;
    }

    public void setAdAttName(String adAttName) {
        this.adAttName = adAttName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdLocalUrl() {
        return this.adLocalUrl;
    }

    public void setAdLocalUrl(String adLocalUrl) {
        this.adLocalUrl = adLocalUrl;
    }
}
