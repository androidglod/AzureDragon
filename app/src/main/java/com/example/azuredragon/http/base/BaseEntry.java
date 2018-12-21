package com.example.azuredragon.http.base;


import com.example.azuredragon.http.utils.MainUtil;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:application
 */

public class BaseEntry<T> {
    private boolean status;
    private String message;
    private T data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
