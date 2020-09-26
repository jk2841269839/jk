package com.letsgo.service;

import com.letsgo.bean.entity.User;

import java.util.Map;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/5 2:11 PM
 */
public interface UserService {
    /**
     * 登录
     * @param user    登录参数 主要是手机号
     * @return          登录状态和登录人信息
     */
    public Map<String ,Object> login(User user);

    /**
     * 添加一条用户
     * @param user user
     * @return true/false
     */
    public Map<String ,Object> addUser(User user);

    /**
     * 查询一条用户
     * @param uuid uuid
     * @return user
     */
    public User getUser(String uuid);

    /**
     * 保存个人资料
     * @param user user
     * @return true/false
     */
    public boolean saveInfo(User user);

    /**
     * 核实人脸信息
     * @param url url
     * @return user
     */
    public User checkFaceId(String url);

    /**
     * 录入人脸信息
     * @param url url
     * @param uname uname
     * @return 1/0
     */
    public Integer faceRegister(String url,String uname);
}
