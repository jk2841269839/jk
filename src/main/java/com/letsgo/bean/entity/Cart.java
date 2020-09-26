package com.letsgo.bean.entity;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/7/14 12:13 PM
 */
public class Cart {
    private String cartId;
    private String userUuid;
    private int goodId;
    private int cartCount;
    private int cartChecked;
    private String goodName;
    private double goodPrice;
    private String goodImg0;

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

    public Cart(String cartId, String userUuid, int goodId, int cartCount, int cartChecked) {
        this.cartId = cartId;
        this.userUuid = userUuid;
        this.goodId = goodId;
        this.cartCount = cartCount;
        this.cartChecked = cartChecked;
    }

    public Cart() {
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public int getCartChecked() {
        return cartChecked;
    }

    public void setCartChecked(int cartChecked) {
        this.cartChecked = cartChecked;
    }
}
