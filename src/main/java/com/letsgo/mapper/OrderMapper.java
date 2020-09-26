package com.letsgo.mapper;

import com.letsgo.bean.entity.Order;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/22 1:52 PM
 */
public interface OrderMapper {
    /**
     * 插入一条订单
     * @param order order
     * @return 0/1
     */
    Integer insertOrder(Order order);

    /**
     * select all order by user uuid
     * @param uuid uuid
     * @return orderList
     */
    List<Order> selectAllOrder(String uuid);
}
