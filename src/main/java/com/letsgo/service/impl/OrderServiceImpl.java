package com.letsgo.service.impl;

import com.letsgo.bean.entity.Order;
import com.letsgo.mapper.OrderMapper;
import com.letsgo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/22 1:50 PM
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper = null;

    @Override
    public boolean addOrder(Order order) {
        Integer i = orderMapper.insertOrder(order);
        if(i > 0){
            System.out.println("\toperated status > " + i);
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getAllOrder(String uuid) {
        return orderMapper.selectAllOrder(uuid);
    }
}
