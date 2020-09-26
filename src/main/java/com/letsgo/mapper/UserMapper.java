package com.letsgo.mapper;

import com.letsgo.bean.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/5 2:13 PM
 */
public interface UserMapper {
    /**
     * 登录
     * @param user
     * @return
     */
    public User selectUser(User user);
    /**
     * 插入一个人
     * @param user    人的信息
     * @return          插入是否成功 返回值大于零代表成功 否则代表插入失败
     */
    public int insertUser(User user);


    /**
     * 根据电话查询用户
     * @param user
     * @return
     */
    public Integer selectUserByMobile(User user);

    /**
     * 通过uuid查询用户信息
     * @param uuid uuid
     * @return user
     */
    public User selectUserById(String uuid);

    /**
     * 保存用户信息
     * @param user user
     * @return 1/0
     */
    public Integer updateUser(User user);

    /**
     * 核实人脸信息
     * @param url url
     * @return user
     */
    public User selectFaceId(String url);

    /**
     * 录入人脸信息
     * @return 1/0
     */
    public Integer insertFaceId(@Param("faceId") String faceId, @Param("userName") String userName);
}
