package com.lingshimall.lingshixiaomiao.beans;

/**
 * Created by 张 波 on 2016/7/7.
 */
public class Goods {
    private String goodPicturePath;
    private String goodName;
    private String goodSurplusTime;
    private String goodPrice;
    private String goodUsedNum;
    private String goodUsedScale;
    private String goodDiscount;
    private String goodCost;

    public Goods(String goodPicturePath, String goodName, String goodSurplusTime, String goodPrice, String goodUsedNum, String goodUsedScale, String goodDiscount, String goodCost) {
        this.goodPicturePath = goodPicturePath;
        this.goodName = goodName;
        this.goodSurplusTime = goodSurplusTime;
        this.goodPrice = goodPrice;
        this.goodUsedNum = goodUsedNum;
        this.goodUsedScale = goodUsedScale;
        this.goodDiscount = goodDiscount;
        this.goodCost = goodCost;
    }

    public String getGoodPicturePath() {
        return goodPicturePath;
    }

    public void setGoodPicturePath(String goodPicturePath) {
        this.goodPicturePath = goodPicturePath;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodSurplusTime() {
        return goodSurplusTime;
    }

    public void setGoodSurplusTime(String goodSurplusTime) {
        this.goodSurplusTime = goodSurplusTime;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodUsedNum() {
        return goodUsedNum;
    }

    public void setGoodUsedNum(String goodUsedNum) {
        this.goodUsedNum = goodUsedNum;
    }

    public String getGoodUsedScale() {
        return goodUsedScale;
    }

    public void setGoodUsedScale(String goodUsedScale) {
        this.goodUsedScale = goodUsedScale;
    }

    public String getGoodDiscount() {
        return goodDiscount;
    }

    public void setGoodDiscount(String goodDiscount) {
        this.goodDiscount = goodDiscount;
    }

    public String getGoodCost() {
        return goodCost;
    }

    public void setGoodCost(String goodCost) {
        this.goodCost = goodCost;
    }
}
