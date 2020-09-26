package com.letsgo.controller;

import com.baidu.aip.face.AipFace;
import com.letsgo.bean.entity.User;
import com.letsgo.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/25 11:26 AM
 */
@Controller
public class FaceLoginController {
    private static final long serialVersionUID = -2935418321710188792L;
    public static final String APP_ID = "22757531";
    public static final String API_KEY = "YiDjGdWawjgowOeYcVMbWRAF";
    public static final String SECRET_KEY = "iawB3ibIaOy8LGGtHjvCvkh7gEFcTzhI";

    @Autowired
    UserService userService;

    @RequestMapping("faceRegister")
    @ResponseBody
    public String faceRegister(HttpServletRequest request) throws ServletException, IOException, InterruptedException {
        System.out.println("Enter >>> faceRegister");
        String uname = request.getParameter("uname");
        String faceBase = request.getParameter("faceBase");
        System.out.println(uname+" "+faceBase);
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", "user's info");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "NORMAL");
        options.put("action_type", "REPLACE");
        String image = faceBase;
        String imageType = "BASE64";
        String groupId = "lzy";
        String userId = uname;
        // 人脸注册
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        System.out.println(res.toString(2));
        System.out.println("--------");
        System.out.println(res.toString());
        if(res.get("error_msg").equals("SUCCESS")) {
            String url = groupId+uname;
            System.out.println("+++++++++++"+url);
            int i = userService.faceRegister(url,uname);
            System.out.println("\t插入状态 > "+i);
            // response.getWriter().print(res.get("error_msg").toString());
        }
        return res.get("error_msg").toString();
    }


    @RequestMapping("faceLogin")
    @ResponseBody
    public String faceLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Enter >>> faceLogin");
        String faceBase = request.getParameter("faceBase");
        System.out.println("\t登录 > "+faceBase);
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "3");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
//        options.put("user_id", "233451");
        options.put("max_user_num", "3");
        String image = faceBase;
        String imageType = "BASE64";
        String groupIdList = "lzy";
        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupIdList, options);
        System.out.println("\tres > "+res.get("error_code"));
        System.out.println(res.toString(2));
        if(res.get("error_msg").equals("SUCCESS")) {
            JSONObject res1 = (JSONObject) res.get("result");
            String newres1 = res1.get("user_list").toString().substring(1, res1.get("user_list").toString().length() - 1);
            JSONObject res2 = new JSONObject(newres1);
            String url = res2.get("group_id").toString() + res2.get("user_id").toString();
            User user = userService.checkFaceId(url);
            if(user != null){
                HttpSession session2 = request.getSession();
                session2.setAttribute("operater", user);
                // response.getWriter().print(res.get("error_msg").toString());
            }
        }
        return res.get("error_msg").toString();
    }

    @RequestMapping("toFaceRegister")
    public ModelAndView toFaceRegister(){
        return new ModelAndView("faceRegister");
    }
}
