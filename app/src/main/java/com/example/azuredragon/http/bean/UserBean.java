package com.example.azuredragon.http.bean;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:  用户
 */
public class UserBean {
    private String username;
    private String password;
    private String password_retry;
    private String email;
    private String sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_retry() {
        return password_retry;
    }

    public void setPassword_retry(String password_retry) {
        this.password_retry = password_retry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
