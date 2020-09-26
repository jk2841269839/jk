package com.letsgo.util.servlet;


import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/6/21 8:48 PM
 */
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 2666978226204158886L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取调用方法的的名字 请求参数中必须有methodName且methodName的值不能为空
        String methodName = request.getParameter("methodName");
        String requestMethod = request.getMethod();
        // System.out.println("请求方式 >>> " + requestMethod);
        // 获取当前对象的Class的对象
        Class<? extends BaseServlet> clazz = this.getClass();
        try {
            // 获取调用的方法
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 设置调用方法的访问权限
            method.setAccessible(true);
            // 执行该方法
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求转发
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param path     请求转发跳转到路径
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    public void forword(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
        return;
    }
    /**
     * 网页重定向
     *
     * @param response HttpServletResponse
     * @param path     网页重定向路径
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    public void redirect(HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(path);
        return;
    }

    /**
     * 添加跳转路径前缀
     *
     * @param path 跳转路径相对地址
     * @return 完整的跳转地址
     */
    public String prefix(String path) {
        return "/WEB-INF/html/" + path;
    }

    /**
     * 添加跳转路径后缀
     *
     * @param path 跳转路径相对地址
     * @return 完整的跳转地址
     */
    public String suffix(String path) {
        return path + ".jsp";
    }

    /**
     * 添加转转前缀和后缀
     * @param path  跳转路径相对地址
     * @return      完整的跳转地址
     */
    public String fullPath(String path) {
        // return prefix(suffix(path));
        // index
        // default.jsp
        // /WEB-INF/html/default.jsp

        return suffix(prefix(path));
        // index
        // /WEB-INF/html/index
        // /WEB-INF/html/default.jsp
    }

    /**
     * 向页面输出字符串
     * @param response      HttpServletResponse
     * @param text          输出的字符串
     * @throws IOException  IOException
     */
    public static void printText(HttpServletResponse response, String text) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print(text);
        writer.flush();
        writer.close();
    }

    /**
     * 将对象转为json字符串向页面输出
     * @param response      HttpServletResponse
     * @param object        输出的对象
     * @throws IOException  IOException
     */
    public void printJson(HttpServletResponse response,Object object) throws IOException {
        printText(response,JSON.toJSONString(object));
    }
}

