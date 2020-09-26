package com.letsgo.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.letsgo.bean.entity.Cart;
import com.letsgo.bean.entity.Good;
import com.letsgo.bean.entity.Order;
import com.letsgo.service.CartService;
import com.letsgo.service.GoodService;
import com.letsgo.service.OrderService;
import com.letsgo.util.alipay.AlipayConfig;
import com.letsgo.util.date.BaseDate;
import com.letsgo.util.uuid.BaseUuid;
import com.thoughtworks.qdox.model.expression.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/21 3:52 PM
 */
@Controller
public class AlipayController {
    @Autowired
    CartService cartService = null;
    @Autowired
    GoodService goodService = null;
    @Autowired
    OrderService orderService = null;

    @RequestMapping("pay")
    public void pay(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("Enter >>> pay");
        String carts = (String) request.getSession().getAttribute("carts");
        String sum = (String) request.getSession().getAttribute("sum");
        carts = carts.replaceAll("\"", "");
        carts = carts.substring(1,carts.length()-1);
        String[] cartList = carts.split(",");
        System.out.println("\tmoney > " + sum);
        System.out.println("\tcartList > " + JSON.toJSONString(cartList));
        String orderId = null;
        Cart cart = null;
        Good good = null;
        String uuid = BaseUuid.getUuid(request);
        for (int i = 0; i < cartList.length ; i++) {

            cart = cartService.findCart(cartList[i]);
            good = goodService.findGood(cart.getGoodId());
            orderId = BaseDate.getDateString(new Date(),"MMddHHmmss") + uuid.substring(uuid.length()-4) + good.getGoodId();

            double price = good.getGoodPrice() * cart.getCartCount();
            Order order = new Order(orderId, uuid, good.getGoodImg0(),new Date(), 11, price, cart.getCartCount()) ;
            orderService.addOrder(order);
            cartService.deleteCart(cartList[i]);
            goodService.addSale(good.getGoodId(),cart.getCartCount());
        }

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //String id = request.getParameter("id");

        String time = BaseDate.getDateString(new Date(),"yyyy-MM-dd HH:mm:ss");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(orderId.getBytes("ISO-8859-1"), "UTF-8");
        // 付款金额，必填
        String total_amount = new String(sum.getBytes("ISO-8859-1"), "UTF-8");
        //订单交易时间
        String  time_deal = new String(time.getBytes("ISO-8859-1"), "UTF-8");
        // 订单名称，必填
        String subject = "支付宝支付";
        // 商品描述，可空
        String body = "支付宝支付";

        //设置请求内容
        // System.out.println(request.getParameter("WIDtotal_amount"));
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","+"\"time_deal\":\"" + time_deal + "\","+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        // 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        // alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        // + "\"total_amount\":\""+ total_amount +"\","
        // + "\"subject\":\""+ subject +"\","
        // + "\"body\":\""+ body +"\","
        // + "\"timeout_express\":\"10m\","
        // + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
        // 请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);
        // 设置编码的方式为html
        response.setContentType("text/html;charset=utf-8");
        // 创建out的对象(打印) 打印出来支付宝给我们自动生成的表单在页面上
//		Map<String, Object> map = new HashMap<String, Object>();
        response.getWriter().write(result);
//		response.getWriter().write(JSON.toJSONString(result));

    }

    @RequestMapping("callBack")
    public void callBack(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException {
        System.out.println("测试");

//			/**
//			 * 支付宝的回调 告诉你 1.支付宝订单号 2.自己商城生成的订单号 3.付款金额
//			 */

        // 商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 付款金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
        //订单交易时间
        // String  time_deal = new String(request.getParameter("time_deal").getBytes("ISO-8859-1"), "UTF-8");
        // 设置相应的编码格式是 html
        response.setContentType("text/html;charset=utf-8");
        System.out.println("订单号:" + out_trade_no);
        System.out.println("支付宝交易号" + trade_no);
        System.out.println("付款金额" + total_amount);
        // System.out.println("交易时间"+time_deal);
        AlipayConfig.logResult("订单号:" + out_trade_no+"\t支付宝交易号" + trade_no+"\t付款金额" + total_amount+"\t交易时间");

        System.out.println("执行完成");
        String time_deal = BaseDate.getDateString(new Date(),"yyyy-MM-dd HH:mm:ss");
        System.out.println("time_deal="+time_deal);

        // response.sendRedirect("http://www.jjking.club/Shopping/alipay.html");
        //		response.sendRedirect("http://localhost:8099/ShoppingE/alipay.html");
        String uuid = BaseUuid.getUuid(request);
        Order order = new Order();
        order.setId(out_trade_no);
        order.setTransactionnumber(trade_no);
        // order.setCommodityprice(total_amount);
        // order.setCreationtime(time_deal);
        order.setUuid(uuid);
        //获得数据
        //插入返回的是影响函数（与查询不同）
        // int i = mapper.addOrder(order);
        // System.out.println(i);
        response.sendRedirect("http://localhost:8080/");
    }
}
