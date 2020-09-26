package com.letsgo.util.uuid;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/15 10:41 AM
 */
public class BaseUuid {
    /**，获取session域中用户的uuid
     * @param request
     * @return
     */
    public static String getUuid(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String operater = null;
        // 遍历 cookie，拿出操作者信息
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("operater")) {
                // 拿到cookie中存的操作者信息
                operater = URLDecoder.decode(cookies[i].getValue());
            }
        }
        JSONObject jsonObject = JSONObject.parseObject(operater);
        // 获取用户的uuid
        return jsonObject.getString("uuid");
    }
}
