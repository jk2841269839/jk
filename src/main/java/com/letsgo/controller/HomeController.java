package com.letsgo.controller;

import com.alibaba.fastjson.JSON;
import com.letsgo.bean.entity.Good;
import com.letsgo.bean.entity.User;
import com.letsgo.service.GoodService;
import com.letsgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/5 2:00 PM
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodService goodService;

    @RequestMapping("/")
    public String toHomePage(HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        System.out.println("进入 >>> toHomePage");
        // 获取页面 session 域
        HttpSession session = request.getSession();
        // 获取 session 域中的用户信息
        if (session.getAttribute("operater") != null) {
            // 如果session中存在操作者信息获取该操作者对象
            User operater = (User) session.getAttribute("operater");
            // cookie中是否存在操作者信息 不存在为true
            boolean flag = true;
            // 如果session中不存在作者信息就是没有登录过那么我看看有没有cookie
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i <cookies.length ; i++) {
                if(cookies[i].getName().equals("operater")){
                    // 如果cookie中存在操作者信息那么修改flag状态为false
                    flag = false;
                }
            }
            if (flag){
                // 当cookie中不存在坐着信息的时候
                // 操作者对象的json字符串加密后存储在cookie中
                Cookie cookie = new Cookie("operater", URLEncoder.encode(JSON.toJSONString(operater),"UTF-8"));
                // cookie有效期 一周
                cookie.setMaxAge(60 * 60 * 24 * 7);
                // 向浏览器设置cookie
                response.addCookie(cookie);
            }
        } else {
            // 如果session中不存在作者信息就是没有登录过那么我看看有没有cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("operater")) {
                        // 如果cookie中存在操作者信息那么获取操作者信息
                        String value = cookies[i].getValue();
                        // 操作者信息解密
                        String decode = URLDecoder.decode(value, "UTF-8");
                        // 将操作者转为Person对象
                        User user = JSON.parseObject(decode, User.class);
                        //调用业务层
                        Map<String, Object> map = userService.login(user);
                        switch (map.get("status").toString()) {
                            case "success": {
                                // 如果登录成功 那么将操作者信息存储到session中
                                User operater = (User) map.get("operater");
                                session.setAttribute("operater", operater);
                                // 重新这是session失效时间
                                session.setMaxInactiveInterval(60 * 30);
                                break;
                            }
                        }
                    }
                }
            }
        }
        // 拿到 12 个新品商品

        List<Good> goods = goodService.getHotGoods();
        model.addAttribute("goods",goods);
        return "home";
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        System.out.println("进入 >>> toLogin");
        return "login";
    }
}
