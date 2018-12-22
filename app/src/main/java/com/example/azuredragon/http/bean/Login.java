package com.example.azuredragon.http.bean;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  login
 */

public class Login {

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
//    // 书币
//    private long gold;
//
//
//    private long giveGold;
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
}
