package com.example.azuredragon.http.base;


import java.util.List;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:application
 */

public class BaseListEntry<T> {
    private boolean status;
    private String message;
    private List<T> data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
