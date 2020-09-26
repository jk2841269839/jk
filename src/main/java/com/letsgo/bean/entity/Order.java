package com.letsgo.bean.entity;

import java.util.Date;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/22 1:46 PM
 */

public class Order {
    /**
     * 商户订单号
     */
    private String id;
    /**
     * 用户编号
     */
    private  String uuid;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date creationtime;
    /**
     * 订单状态
     */
    private int status;
    /**
     * 商品总价
     */
    private double commodityprice;
    /**
     * 支付宝交易号（唯一交易号）
     */
    private String transactionnumber;
    private String addId;
    private String goodId;
    private int count;



    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    public Order(){

    }
    public Order(String id, String uuid, String name, Date creationtime, int status, double commodityprice, int count) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.creationtime = creationtime;
        this.status = status;
        this.commodityprice = commodityprice;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getCommodityprice() {
        return commodityprice;
    }

    public void setCommodityprice(double commodityprice) {
        this.commodityprice = commodityprice;
    }

}
