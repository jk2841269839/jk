package com.letsgo.mapper;

import com.letsgo.bean.entity.Address;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/17 5:01 PM
 */
public interface AddressMapper {

    /**
     * 添加一条收货地址
     * @param address
     * @return
     */
    public int insertAddress(Address address);

    /**
     * 通过用户编号查询该用户所有收货地址
     * @param uuid
     * @return
     */
    public List<Address> selectAllAddress(String uuid);

    /**
     * 更新地址信息
     * @param address
     * @return
     */
    public int updateAddress(Address address);

    /**
     * 删除地址
     * @param id
     * @return
     */
    public Integer deleteAddress(String id);
}
