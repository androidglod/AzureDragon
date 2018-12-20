package com.example.azuredragon.http.bean;

import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  login
 */

public class BaseBean<T> {

    /**
     * id : 18
     * name : allen123
     * type : 0
     * createTime : 2018-06-27 10:18:47
     * phoneNumber : 17052732808
     * headPath : http://image.tv188.com/images/member/head_image.jpg
     * tokenId : 4761182680843567127
     */
    private String message;
    private boolean status;
    private List<T> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
