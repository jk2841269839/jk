package com.letsgo.service.impl;

import com.letsgo.bean.entity.Address;
import com.letsgo.mapper.AddressMapper;
import com.letsgo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/17 5:04 PM
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> showAllAddress(String uuid) {
        return addressMapper.selectAllAddress(uuid);
    }

    @Override
    public Integer addAddress(Address address) {
        return addressMapper.insertAddress(address);
    }

    @Override
    public boolean editAddress(Address address) {
        Integer i = addressMapper.updateAddress(address);
        System.out.println("\toperated status > " + i);
        if(i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAddress(String id) {
        Integer i = addressMapper.deleteAddress(id);
        System.out.println("\toperated status > " + i);
        if(i > 0) {
            return true;
        }
        return false;
    }
}
