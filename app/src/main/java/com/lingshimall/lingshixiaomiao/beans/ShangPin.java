package com.lingshimall.lingshixiaomiao.beans;

/**
 * Created by 张 波 on 2016/7/12.
 */
public class ShangPin {
    private String goods;
    private int price;
    private String picture;
    private int shangpin_id;

    public ShangPin() {
    }

    public ShangPin(String goods, int price, String picture,int shangpin_id) {
        this.goods = goods;
        this.price = price;
        this.picture = picture;
        this.shangpin_id=shangpin_id;

    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getShangpin_id() {
        return shangpin_id;
    }

    public void setShangpin_id(int shangpin_id) {
        this.shangpin_id = shangpin_id;
    }
}
