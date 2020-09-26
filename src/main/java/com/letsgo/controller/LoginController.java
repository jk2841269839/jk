package com.letsgo.controller;


import cn.hutool.core.util.IdUtil;
import com.letsgo.bean.entity.User;
import com.letsgo.service.UserService;
import com.letsgo.util.phone.BasePhone;
import com.letsgo.util.phone.SMS;
import com.letsgo.util.servlet.BaseServlet;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/6 2:50 PM
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录-发送验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("sendSms")
    public void sendSms(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("进入 >>> sendSms");
        // 从页面获取手机号
        String mobile = request.getParameter("mobile");
        // 发送短信
        String phoneCode = BasePhone.phoneCode(mobile);
        // 讲返回的json字符串转为对象
        SMS sms = JSON.parseObject(phoneCode, SMS.class);
        // 获取对象中的状态码
        String return_code = sms.getReturn_code();
        if ("00000".equals(return_code)){
            HttpSession session = request.getSession();
            // 将发送短信的手机号存储到session中
            session.setAttribute("mobile",mobile);
            // 将短信中的验证码存储到session中
            session.setAttribute("code",sms.getCode());
            // 设置session失效时间 60秒
            session.setMaxInactiveInterval(60);
        }
        BaseServlet.printText(response,phoneCode);
    }

    /**
     * 登录功能
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("进入 >>> login");
        // 获取页面发送的手机号
        String mobile = request.getParameter("mobile");
        // 获取页面发送的验证码
        String code = request.getParameter("code");
        if (!StringUtils.isEmpty(mobile) && !StringUtils.isEmpty(code)) {
            // 页面获取到的手机号和验证码不为空获取session
            HttpSession session = request.getSession();
            // 获取session中的手机号
            String mobileInSession = session.getAttribute("mobile").toString();
            // 获取session中的验证码
            String codeInSession = session.getAttribute("code").toString();
            if (!StringUtils.isEmpty(mobileInSession) && !StringUtils.isEmpty(codeInSession)) {
                // session获取到的手机号和验证码不为空判断页面和session中的手机号和密码是否一致
                if (mobile.equals(mobileInSession) && code.equals(codeInSession)) {
                    // 实例化Person
                    User user = new User();
                    // 将获取到的手机号存储在Person对象中
                    user.setMobile(mobile);
                    // session获取到的手机号和验证码不为空判断页面和session中的手机号和密码一致调用业务层
                    Map<String, Object> map = userService.login(user);
                    switch (map.get("status").toString()) {
                        case "success" -> {
                            // 如果登录成功 那么将操作者信息存储到session中
                            User operater = (User) map.get("operater");
                            session.setAttribute("operater", operater);
                            // 重新这是session失效时间
                            session.setMaxInactiveInterval(60 * 30);
                            break;
                        }
                        case "failed" -> {
                            // 登录失败后 讲该手机号存入数据库
                            user.setName(user.getMobile());
                            user.setUuid(IdUtil.fastSimpleUUID());
                            user.setRole(21);
                            Map<String, Object> addUser = userService.addUser(user);
                            switch (addUser.get("status").toString()) {
                                case "success": {
                                    System.out.println("新增成功");
                                    // 新增成功后递归调用登录
                                    login(request, response);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    // 响应返回信息
                } else {
                    // session获取到的手机号和验证码不为空判断页面和session中的手机号和密码map不一致响应错误信息
                }
            } else {
                // session失效 即验证码长期未使用响应错误信息
            }
        } else {
            // 页面获取到的手机号或者验证码为空向页面响应错误信息
        }
    }

    @RequestMapping("toFaceLogin")
    public ModelAndView faceLogin(){
        ModelAndView modelAndView = new ModelAndView("faceLogin");
        return modelAndView;
    }
}
