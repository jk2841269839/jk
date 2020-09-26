package com.letsgo.bean.entity;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/7/2 8:59 PM
 */
public class Good {
    private int goodId ;
    private String goodName;
    private double goodPrice;
    private String goodImg0;
    private String goodImg1;
    private String goodImg2;
    private String goodImg3;
    private String goodImg4;
    private String goodBrand;
    private String goodDetail1;
    private String goodSubtitle;
    private int goodStatus;
    private int goodStock;

    public String getGoodBrand() {
        return goodBrand;
    }

    public void setGoodBrand(String goodBrand) {
        this.goodBrand = goodBrand;
    }

    public String getGoodSubtitle() {
        return goodSubtitle;
    }

    public void setGoodSubtitle(String goodSubtitle) {
        this.goodSubtitle = goodSubtitle;
    }

    public int getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(int goodStatus) {
        this.goodStatus = goodStatus;
    }

    public int getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(int goodStock) {
        this.goodStock = goodStock;
    }

    public String getGoodDetail1() {
        return goodDetail1;
    }

    public void setGoodDetail1(String goodDetail1) {
        this.goodDetail1 = goodDetail1;
    }

    private int specId;

    public String getGoodImg2() {
        return goodImg2;
    }

    public void setGoodImg2(String goodImg2) {
        this.goodImg2 = goodImg2;
    }

    public String getGoodImg3() {
        return goodImg3;
    }

    public void setGoodImg3(String goodImg3) {
        this.goodImg3 = goodImg3;
    }

    public String getGoodImg4() {
        return goodImg4;
    }

    public void setGoodImg4(String goodImg4) {
        this.goodImg4 = goodImg4;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public String getGoodImg1() {
        return goodImg1;
    }

    public void setGoodImg1(String goodImg1) {
        this.goodImg1 = goodImg1;
    }

    public Good() {
    }

    public Good(int goodId, String goodName, double goodPrice, String goodImg0, String goodImg1) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodImg0 = goodImg0;
        this.goodImg1 = goodImg1;

    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodImg0() {
        return goodImg0;
    }

    public void setGoodImg0(String goodImg0) {
        this.goodImg0 = goodImg0;
    }
}
