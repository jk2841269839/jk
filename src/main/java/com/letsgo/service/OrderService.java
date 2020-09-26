package com.letsgo.service;

import com.letsgo.bean.entity.Order;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/22 1:48 PM
 */
public interface OrderService {
    /**
     * 添加一条订单
     * @param order order
     * @return true/false
     */
    boolean addOrder(Order order);

    /**
     * get all order by user uuid
     * @param uuid uuid
     * @return orderList
     */
    List<Order> getAllOrder(String uuid);
}
