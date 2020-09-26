package com.letsgo.mapper;

import com.letsgo.bean.entity.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/5 3:59 PM
 */
public interface GoodMapper {
    /**
     * 主页-随机获取一件热销商品
     * @return
     */
    public Good selectHot();

    /**
     * 通过商品编号查询商品所有信息
     * @param goodId
     * @return
     */
    public Good selectGoodDetail(int goodId);

    /**
     * 通过分类编号查询分类名称
     * @param specId
     * @return
     */
    public String selectSpecName(int specId);

    /**
     * 通过分类编号查询所有该类商品
     * @param good    Good
     * @return          List<Good>
     */
    public List<Good> selectGoods(Good good);

    /**
     * 通过商品编号查询商品
     * @param goodId goodId
     * @return good
     */
    public Good selectGood(Integer goodId);

    /**
     * 更新销量
     * @param goodId goodId
     * @param count count
     * @return 1/0
     */
    public Integer updateSale(@Param("goodId")Integer goodId, @Param("count")Integer count);

    /**
     * 模糊查询
     * @param goodName goodName
     * @return goodList
     */
    public List<Good> selectSomeGood(String goodName);
}
