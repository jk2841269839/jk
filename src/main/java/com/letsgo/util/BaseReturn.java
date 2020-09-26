package com.letsgo.util;

import com.letsgo.bean.dto.ReturnResult;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/6/21 3:13 PM
 */
public class BaseReturn {
    public static ReturnResult returnSuccess(){
        ReturnResult result=new ReturnResult();
        result.setCode("200");
        System.out.println("\toperation status > " + "200");
        return result;
    }

    public static ReturnResult returnSuccessWithData(String code,String msg){
        ReturnResult result=new ReturnResult();
        result.setCode(code);
        result.setMsg(msg);
        System.out.println("\toperation status > " + code);
        return result;
    }

    public static ReturnResult returnSuccessWithData(String code,Object data){
        ReturnResult result=new ReturnResult();
        result.setCode(code);
        result.setData(data);
        System.out.println("\toperation status > " + code);
        return result;
    }

    public static ReturnResult returnSuccessWithData(String code,String msg,Object data){
        ReturnResult result=new ReturnResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        System.out.println("\toperation status > " + code);
        return result;
    }

    public static ReturnResult returnFailed(String code,String msg){
        ReturnResult result=new ReturnResult();
        result.setCode(code);
        result.setMsg(msg);
        System.out.println("\toperation status > " + code);
        return result;
    }

    public static ReturnResult returnFailedWithData(String code,String msg,Object data){
        ReturnResult result=new ReturnResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        System.out.println("\toperation status > " + code);
        return result;
    }
}
