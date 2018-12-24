package com.example.azuredragon.http.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  login
 */

public class LoginBean implements Serializable {

    /**
     * id : 18
     * name : allen123
     * type : 0
     * createTime : 2018-06-27 10:18:47
     * phoneNumber : 17052732808
     * headPath : http://image.tv188.com/images/member/head_image.jpg
     * tokenId : 4761182680843567127
     */
    private String userName;
    private String mobile;
    // 书币
    private BigDecimal gold;
    // 性别
    private int sex;


    private BigDecimal giveGold;
    private int state;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    public BigDecimal getGiveGold() {
        return giveGold;
    }

    public void setGiveGold(BigDecimal giveGold) {
        this.giveGold = giveGold;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
