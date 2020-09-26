package com.letsgo.controller;

import com.letsgo.bean.dto.ReturnResult;
import com.letsgo.bean.entity.Cart;
import com.letsgo.service.CartService;
import com.letsgo.util.BaseReturn;
import com.letsgo.util.date.BaseDate;
import com.letsgo.util.uuid.BaseUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/15 10:40 AM
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 添加至购物车
     * @param request
     * @return
     */
    @RequestMapping("addCart")
    @ResponseBody
    public String addCart(HttpServletRequest request) {
        System.out.println("Enter >>> addCart");
        // 获取商品编号
        int goodId = Integer.parseInt(request.getParameter("goodId"));
        // 获取商品数量
        int goodCount = Integer.parseInt(request.getParameter("count"));
        System.out.println("\tgoodId > " + goodId);
        System.out.println("\tgoodCount > " + goodCount);
        // 获取用户编号
        String uuid = BaseUuid.getUuid(request);
        // 生成购物车编号
        String cartId = BaseDate.getDateString(new Date(),"MMddHHmmss") + uuid.substring(uuid.length()-4);
        // 准备 sql 语句参数
        Cart cart = new Cart(cartId, uuid, goodId, goodCount, 0);
        try{
            if(cartService.addCart(cart)){
                return "succeed";
            } else {
                return "failed";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 显示购物车
     * @param request
     * @return
     */
    @RequestMapping("showCarts")
    public ModelAndView showCarts(HttpServletRequest request) {
        System.out.println("Enter >>> showCart");
        ModelAndView modelAndView = new ModelAndView();
        // 获取用户编号
        String uuid = BaseUuid.getUuid(request);
        // 查询
        List<Cart> cartList = cartService.showCarts(uuid);
        System.out.println("\tcartListSize > " + cartList.size());
        // 将查询信息放入request域中
        modelAndView.addObject("cartList",cartList);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    /**
     * 删除购物车
     * @param cartId cartId
     * @return true/false
     */
    @RequestMapping("deleteCart/{cartId}")
    @ResponseBody
    public String  deleteCart(@PathVariable String cartId) {
        System.out.println("Enter >>> deleteCart");
        System.out.println("\tdeleteId > " + cartId);
        if(cartService.deleteCart(cartId)){
            return "succeed";
        }
        return "failed";
    }
}
