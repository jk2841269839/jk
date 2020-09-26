package com.letsgo.controller;

import com.github.pagehelper.PageHelper;
import com.letsgo.bean.entity.Good;
import com.letsgo.bean.model.OrderBy;
import com.letsgo.bean.model.PageParam;
import com.letsgo.service.GoodService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/14 3:48 PM
 */
@Controller
public class GoodController {
    @Autowired
    private GoodService goodService;

    /**
     * 显示商品详情
     * @param goodId
     * @param model
     * @return
     */
    @RequestMapping("showGood/{goodId}")
    public String showGood(@PathVariable String goodId, Model model) {
        System.out.println("Enter >>> showGood");
        System.out.println("\tgoodId > " + goodId);
        Map<String ,Object> parmas = new HashMap<>();
        parmas = goodService.selectGoodById(Integer.parseInt(goodId));
        model.addAttribute("good",parmas.get("good"));
        model.addAttribute("specName",parmas.get("specName"));
        System.out.println("\t" + parmas.get("specName"));
        return "single-product";
    }

    /**
     * 显示商品详情
     * @return
     */
    @RequestMapping(value = "getGoodList/{pageNum}/{pageSize}/{specId}/{collation}")
    public String getGoodList(@PathVariable String pageNum,@PathVariable String pageSize,
                              @PathVariable String specId,@PathVariable String collation,Model model) {
        System.out.println("Enter >>> getGoodList");
        System.out.println("\tpageNum > " + pageNum);
        System.out.println("\tpageSize > " + pageSize);
        System.out.println("\tspecId > " + specId);
        System.out.println("\tcollation > " + collation);
        int rule = Integer.parseInt(collation);
        Map<String ,Object> parmas = new HashMap<>();
        // 查询参数
        Good good = new Good();
        good.setSpecId(Integer.parseInt(specId));
        parmas.put("good",good);
        parmas.put("pageSize",pageSize);
        // 分页参数
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(Integer.valueOf(pageSize));
        if (!StringUtils.isEmpty(pageNum)) {
            // 如果获取到的查询页面号不是空的那么将查询页面号传入PageParam对象中
            pageParam.setPageNum(Integer.parseInt(pageNum));
        }
        // 设置排序规则
        switch (rule){
            case 1 :
                pageParam.setOrderBy("good_id");
                pageParam.setAscOrDesc(OrderBy.ASC);
                break;
            case 2:
                pageParam.setOrderBy("good_name");
                pageParam.setAscOrDesc(OrderBy.ASC);
                break;
            case 3:
                pageParam.setOrderBy("good_price");
                pageParam.setAscOrDesc(OrderBy.ASC);
                break;
            case 4:
                pageParam.setOrderBy("good_price");
                pageParam.setAscOrDesc(OrderBy.DESC);
                break;
        }
        parmas.put("pageParam",pageParam);
        // 查询符合规格的商品并存放在PageInfo中
        PageInfo<Good> goodPageInfo = goodService.selectGoods(parmas);
        // 将查询结果存储在request域
        model.addAttribute("collation" ,collation);
        model.addAttribute("goodPageInfo" ,goodPageInfo);
        model.addAttribute("specId", specId);
        // 将查询结果打印在后端查看
        System.out.println("\tgoodListSize > " + goodPageInfo.getSize());
        // 页面跳转
        return "shop-list";
    }

    /**
     * 模糊查询商品
     * @param goodName goodName
     * @return goodList
     */
    @RequestMapping("findGoods/{goodName}/{pageNum}/{pageSize}/{collation}")
    public String findGoods(@PathVariable String goodName, @PathVariable String pageNum,
                            @PathVariable String pageSize, @PathVariable String collation,Model model) {
        System.out.println("Enter >>> findGoods");
        System.out.println("\tgoodName > " + goodName);
        System.out.println("\tpageNum > " + pageNum);
        System.out.println("\tpageSize > " + pageSize);
        System.out.println("\tcollation > " + collation);
        int rule = Integer.parseInt(collation);
        // 分页参数
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(Integer.valueOf(pageSize));
        if (!StringUtils.isEmpty(pageNum)) {
            // 如果获取到的查询页面号不是空的那么将查询页面号传入PageParam对象中
            pageParam.setPageNum(Integer.parseInt(pageNum));
        }
        // 设置排序规则
        switch (rule){
            case 1 :
                pageParam.setOrderBy("good_id");
                pageParam.setAscOrDesc(OrderBy.ASC);
                break;
            case 2:
                pageParam.setOrderBy("good_name");
                pageParam.setAscOrDesc(OrderBy.ASC);
                break;
            case 3:
                pageParam.setOrderBy("good_price");
                pageParam.setAscOrDesc(OrderBy.ASC);
                break;
            case 4:
                pageParam.setOrderBy("good_price");
                pageParam.setAscOrDesc(OrderBy.DESC);
                break;
        }
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
        List<Good> goodList = goodService.findGoods(goodName);
        // 返回封装后的对象 PageInfo对象
        PageInfo<Good> goodPageInfo = new PageInfo<>(goodList);
        // 将查询结果存储在request域
        model.addAttribute("collation" ,collation);
        model.addAttribute("goodPageInfo" ,goodPageInfo);
        // 将查询结果打印在后端查看
        System.out.println("\tgoodListSize > " + goodPageInfo.getSize());
        // 页面跳转
        return "shop-list";
    }
}
