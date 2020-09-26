package com.letsgo.service;

import com.github.pagehelper.PageInfo;
import com.letsgo.bean.entity.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/5 4:08 PM
 */
public interface GoodService {
    /**
     * 获得热销商品
     */
    List<Good> getHotGoods();

    /**
     * 根据商品编号查询该商品详细信息
     */
    public Map<String, Object> selectGoodById(int goodId);

    /**
     * 查询所有商品简略信息
     * @param parmas    查询参数 主要是手机号
     * @return          所有商品的简略信息
     */
    public PageInfo<Good> selectGoods(Map<String ,Object> parmas);

    /**
     * 通过商品id查询商品信息
     * @param goodId goodId
     * @return Good
     */
    public Good findGood(Integer goodId);

    /**
     * 增加商品销量
     * @param goodId goodId
     * @param cartCount count
     * @return true/false
     */
    public boolean addSale(Integer goodId, Integer cartCount);

    /**
     * 模糊查询商品
     * @param goodName goodName
     * @return goodList
     */
    public List<Good> findGoods(String goodName);

}
