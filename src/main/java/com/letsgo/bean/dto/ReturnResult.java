package com.letsgo.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/6/21 3:13 PM
 */
@ApiModel
public class ReturnResult {
    @ApiModelProperty("状态码")
    private String code;
    @ApiModelProperty("描述信息")
    private String msg;
    @ApiModelProperty("返回的数据")
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
