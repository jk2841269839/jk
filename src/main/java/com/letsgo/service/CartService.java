package com.letsgo.service;

import com.letsgo.bean.entity.Cart;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/15 10:45 AM
 */
public interface CartService {
    /**
     * 添加购物车
     * @param cart cart
     * @return true/false
     */
    boolean addCart(Cart cart);

    /**
     * 显示购物车
     * @param uuid uuid
     * @return cartList
     */
    List<Cart> showCarts(String uuid);

    /**
     * 删除购物车
     * @param cartId cartId
     * @return true/false
     */
    boolean deleteCart(String cartId);

    /**
     * 根据购物车id查询购物车信息
     * @param cartId cartId
     * @return cart
     */
    Cart findCart(String cartId);


}
