package com.letsgo.service.impl;

import com.letsgo.mapper.UserMapper;
import com.letsgo.bean.entity.User;
import com.letsgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/5 2:11 PM
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>();
        // 默认登录失败
        map.put("status", "failed");
        // 调用持久层

        User operater = userMapper.selectUser(user);

        if (operater != null) {
            // 登录成功
            map.put("status", "success");
            // 存储操作人信息
            map.put("operater", operater);
        }

        // 返回登录状态
        return map;
    }

    @Override
    public Map<String, Object> addUser(User user) {
        Map<String, Object> map = new HashMap<>();
        // 默认插入失败
        map.put("status", "failed");

        try {
            int status = userMapper.insertUser(user);
            ///////因为总是莫名返回0，故在增加一条查询
             status = userMapper.selectUserByMobile(user);
            System.out.println("插入用户" + status);
            if (status > 0) {
                // 插入成功
                map.put("status", "success");
            }
        } catch (Exception exception) {
           exception.printStackTrace();
        } finally {
            return map;
        }
    }

    @Override
    public User getUser(String uuid) {
        return userMapper.selectUserById(uuid);
    }

    @Override
    public boolean saveInfo(User user) {
        if(userMapper.updateUser(user) > 0){
            return true;
        }
        return false;
    }

    @Override
    public User checkFaceId(String url) {
        return userMapper.selectFaceId(url);
    }

    @Override
    public Integer faceRegister(String url, String uname) {
        return userMapper.insertFaceId(url,uname);
    }


}
