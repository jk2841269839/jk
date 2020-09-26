package com.letsgo.controller;

import com.alibaba.fastjson.JSON;
import com.letsgo.bean.entity.Order;
import com.letsgo.bean.entity.User;
import com.letsgo.service.OrderService;
import com.letsgo.service.UserService;
import com.letsgo.util.uuid.BaseUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/23 9:41 AM
 */
@Controller
public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @RequestMapping("myAccount")
    public ModelAndView myAccount(HttpServletRequest request) {
        System.out.println("Enter >>> myAccount");
        ModelAndView modelAndView = new ModelAndView();

        // 获取用户uuid
        String uuid = BaseUuid.getUuid(request);
        // 查询数据库
        User user = userService.getUser(uuid);
        if(user.getNickName()==null){
            user.setNickName("请填写");
        }if(user.getEmail()==null){
            user.setEmail("请填写");
        }if(user.getIdCard()==null){
            user.setIdCard("请填写");
        }
        // 查询用户所有订单
        List<Order> orderList = orderService.getAllOrder(uuid);
        // 将查询结果存储在request域
        modelAndView.addObject("user",user);
        modelAndView.addObject("orderList",orderList);
        // 设置页面跳转，跳转至默认第一个页面
        request.setAttribute("page" ,"pills-dashboard-tab");
        modelAndView.setViewName("my-account");
        return modelAndView;
    }

    @RequestMapping("saveInfo")
    @ResponseBody
    public String saveInfo(User user,HttpServletRequest request) {
        System.out.println("Enter >>> saveInfo");
        System.out.println("\tuser > " + JSON.toJSONString(user));
        String uuid = BaseUuid.getUuid(request);
        user.setUuid(uuid);
        if(userService.saveInfo(user)){
            return "succeed";
        }
        return "failed";
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("operater");
        Cookie cookie = new Cookie("operater",null);
        //设置存活时间，“0”即马上消失
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
