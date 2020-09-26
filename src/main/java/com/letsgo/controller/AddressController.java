package com.letsgo.controller;

import com.alibaba.fastjson.JSON;
import com.letsgo.bean.entity.Address;
import com.letsgo.service.AddressService;
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
 * @date 2020/9/17 5:06 PM
 */
@Controller
public class AddressController {
    @Autowired
    AddressService addressService;
    /**
     * 结账功能
     * @param request request
     * @return mv
     */
    @RequestMapping("checkOut")
    @ResponseBody
    public String checkOut(HttpServletRequest request){
        System.out.println("Enter >>> checkOut");
        String sum = request.getParameter("sum");
        String carts = request.getParameter("carts");
        System.out.println("\tcarts > " + carts);
        System.out.println("\tsum > " + sum);
        HttpSession session = request.getSession();
        session.setAttribute("carts",carts);
        session.setAttribute("sum",sum);
        return "succeed";
    }

    /**
     * 结账功能
     * @param request request
     * @return mv
     */
    @RequestMapping("showAddress")
    public ModelAndView showAddress(HttpServletRequest request){
        System.out.println("Enter >>> showAddress");
        List<Address> addressList = addressService.showAllAddress(BaseUuid.getUuid(request));
        System.out.println("\taddressListSize > " + addressList.size());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("address");
        modelAndView.addObject("addressList",addressList);
        return modelAndView;
    }

    /**
     * 收货地址功能-添加收货地址
     * @throws Exception
     */
    @RequestMapping("addAddress")
    @ResponseBody
    public String addAddress(Address address,HttpServletRequest request) throws Exception {
        System.out.println("进入 >>> addAddress");
        // 获取输入信息
        System.out.println(JSON.toJSONString(address));
        // 获取用户编号
        String uuid = BaseUuid.getUuid(request);
        String addressId = BaseDate.getDateString(new Date(),"MMddHHmmss") + uuid.substring(uuid.length()-4);
        // 准备 sql 语句参数
        address.setUuid(uuid);
        address.setId(addressId);
        // 查询
        int i = addressService.addAddress(address);
        System.out.println("\toperated status > " + i);
        return "succeed";
    }

    /**
     * 收货地址功能-编辑收货地址
     * @throws Exception
     */
    @RequestMapping("editAddress")
    @ResponseBody
    public String editAddress(Address address,HttpServletRequest request) throws Exception {
        System.out.println("Enter >>> editAddress");
        // 获取输入信息
        System.out.println("\tModel > " + JSON.toJSONString(address));
        // 获取用户编号
        if(addressService.editAddress(address)){
            return "succeed";
        }
        return "failed";
    }

    /**
     * 收货地址功能-删除收货地址
     * @throws Exception
     */
    @RequestMapping("deleteAddress")
    @ResponseBody
    public String deleteAddress(String id) {
        System.out.println("Enter >>> deleteAddress");
        // 获取输入信息
        System.out.println("\toperated id > " + id);
        // 获取用户编号
        if(addressService.deleteAddress(id)){
            return "succeed";
        }
        return "failed";
    }
}
