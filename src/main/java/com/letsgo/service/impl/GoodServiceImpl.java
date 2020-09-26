package com.letsgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letsgo.bean.model.PageParam;
import com.letsgo.mapper.GoodMapper;
import com.letsgo.bean.entity.Good;
import com.letsgo.service.GoodService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/5 4:09 PM
 */
@Service("goodService")
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper;
    @Override
    public List<Good> getHotGoods() {
        List<Good> goods = new ArrayList<>();
        // 多次查询直到添加 12 条新品商品为止
        while (goods.size() < 12){
            Good newGood = goodMapper.selectHot();
            boolean flag = true;
            // 防止查询到重复商品
            for (Good good: goods) {
                if(good.getGoodId()==newGood.getGoodId()){
                    flag = false;
                }
            }
            if(flag){
                goods.add(newGood);
            }
        }
        return goods;
    }

    /**
     * 根据商品id查询商品详情
     * @param goodId
     * @return
     */
    @Override
    public Map<String, Object> selectGoodById(int goodId) {
        Map<String, Object> map = new HashMap<>();
        // 通过商品编号从数据库查询
        Good good = goodMapper.selectGoodDetail(goodId);
        // 将查询结果放入 map 中
        map.put("good", good);
        // 通过分类编号查询分类名称
        String specName = goodMapper.selectSpecName(good.getSpecId());
        // 将分类名称放入 map 中
        map.put("specName", specName);
        return map;
    }

    /**
     * @param parmas    查询参数 主要是手机号
     * @return
     */
    @Override
    public PageInfo<Good> selectGoods(Map<String, Object> parmas) {
        // 获取连接
        PageParam pageParam = (PageParam) parmas.get("pageParam");
        // 排序字段 参数封装
        String orderBy = "";
        if (!StringUtils.isEmpty(pageParam.getOrderBy())) {
            orderBy += pageParam.getOrderBy();
            orderBy += " ";
            orderBy += pageParam.getAscOrDesc();
            // 传入查询页码和每页记录数 注意代码顺序
            // 如果有排序字段 那么按照排序字段和排序规则排序
            PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), orderBy);
        } else {
            PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), "good_id asc");
        }
        List<Good> goodList = goodList = goodMapper.selectGoods((Good) parmas.get("good"));
        // 返回封装后的对象 PageInfo对象
        return new PageInfo<Good>(goodList);
    }

    @Override
    public Good findGood(Integer goodId) {
        return goodMapper.selectGood(goodId);
    }

    @Override
    public boolean addSale(Integer goodId, Integer cartCount) {
        Integer i = goodMapper.updateSale(goodId,cartCount);
        if(i > 0) {
            System.out.println("\toperated status > " + i);
            return true;
        }
        return false;
    }

    @Override
    public List<Good> findGoods(String goodName) {
        return goodMapper.selectSomeGood(goodName);
    }
}
