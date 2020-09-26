package com.letsgo.service.impl;

import com.letsgo.mapper.CartMapper;
import com.letsgo.bean.entity.Cart;
import com.letsgo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/15 10:49 AM
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    /**
     * 添加购物车
     * @param cart cart
     * @return true/false
     */
    @Override
    public boolean addCart(Cart cart) {
        Integer i = cartMapper.insertCart(cart);
        if(i > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Cart> showCarts(String uuid) {
        return cartMapper.selectCarts(uuid);
    }

    @Override
    public boolean deleteCart(String cartId){
        if(cartMapper.deleteCart(cartId) > 0){
            return true;
        }
        return false;
    }

    @Override
    public Cart findCart(String cartId) {
        return cartMapper.selectCart(cartId);
    }
}
