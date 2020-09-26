package com.letsgo.bean.entity;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/6/21 3:13 PM
 */
public class User {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 唯一标识符
     */
    private String uuid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 曾用名
     */
    private String nickName;
    /**
     * 现用名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 当下居住住址
     */
    private Integer address;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 角色 11商家 21会员
     */
    private Integer role;
    /**
     * 人脸识别是否绑定 未绑定则为null
     */
    private String accessToken;
    /**
     * 电子邮件
     */
    private String email;

    public User() {
    }

    public User(String uuid) {
        this.uuid = uuid;
    }

    public User(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
