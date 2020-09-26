package com.letsgo.util.phone;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import cn.hutool.setting.dialect.Props;
import org.apache.http.util.EntityUtils;
/**
 * @author 荆康
 * @version 1.0
 * @date 2020/6/22 2:53 PM
 */
public abstract class BasePhone {

    private static Props props = new Props("phone.properties");
    private static String host = props.getProperty("host");
    private static String path = props.getProperty("path");
    private static String method = props.getProperty("method");
    private static String appcode = props.getProperty("appcode");
    private static String tpl_id = props.getProperty("tpl_id");

    /**
     * 向手机发送验证码的方法
     */
    public static String phoneCode(String phone) {
        SMS sms = new SMS();
        Map<String, String> headers = new HashMap<String, String>();
        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        // 验证码 四个随机数字
        String codeNumer = RandomStringUtils.randomNumeric(4);
        System.out.println("验证码 >>> " + codeNumer);
        // 发送验证码格式
        String code = "code:" + codeNumer;
        querys.put("param", code);
        querys.put("tpl_id", tpl_id);
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            /**
             * 重要提示如下: HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse resp = BaseHttp.doPost(host, path, method, headers, querys, bodys);
            // System.out.println(resp.toString());
            // 获取response的body
            // System.out.println(EntityUtils.toString(response.getEntity()));
            // 状态行
            StatusLine statusLine = resp.getStatusLine();
            // 获取响应的状态码
            int statusCode = statusLine.getStatusCode();
            // 解析状态
            String reasonPhrase = statusLine.getReasonPhrase();
            // 获取返回的json字符串
            String text = EntityUtils.toString(resp.getEntity());
            // 将返回的json字符串封装到对象对象中
            sms = JSON.parseObject(text, SMS.class);
            if ("OK".equalsIgnoreCase(reasonPhrase) && 200 == statusCode && "00000".equals(sms.getReturn_code())) {
                // 短信发送成功 则将验证码封装到对象中
                sms.setCode(codeNumer);
                // 将对象的json字符串返回
                return JSON.toJSONString(sms);
            }else{
                // 短信发送失败 直接返回包含错误码的对象的json字符串
                return JSON.toJSONString(sms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(sms);
        }
    }

}

