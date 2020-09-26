package com.letsgo.service;

import com.letsgo.bean.entity.Address;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/17 5:02 PM
 */
public interface AddressService {
    /**
     * 显示用户所有地址
     * @param uuid uuid
     * @return addressList
     */
    List<Address> showAllAddress(String uuid);

    /**
     * 添加地址
     * @param address address
     * @return 1/0
     */
    Integer addAddress(Address address);

    /**
     * 编辑地址
     * @param address address
     * @return true/false
     */
    boolean editAddress(Address address);

    /**
     * 删除地址
     * @param id
     * @return
     */
    boolean deleteAddress(String id);
}
