package com.letsgo.mapper;

import com.letsgo.bean.entity.Cart;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/15 10:55 AM
 */
public interface CartMapper {
    /**
     * 新增购物车
     * @param cart cart
     * @return Integer
     */
    Integer insertCart(Cart cart);

    /**
     * 显示购物车
     * @param uuid uuid
     * @return cartList
     */
    List<Cart> selectCarts(String uuid);

    /**
     * 删除购物车
     * @param cartId cartId
     * @return cartList
     */
    Integer deleteCart(String cartId);

    /**
     * 查询购物车
     * @param cartId cartId
     * @return cart
     */
    Cart selectCart(String cartId);
}
