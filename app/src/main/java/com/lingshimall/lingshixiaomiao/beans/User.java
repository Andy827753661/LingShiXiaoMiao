package com.lingshimall.lingshixiaomiao.beans;

import android.net.Uri;

import java.net.URL;
import java.util.List;

/**
 * Created by zhai on 2016/7/11.
 */
public class User {
    private String userName;
    private String img_url;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}