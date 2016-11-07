package com.zuilot.chaoshengbo.javabean;

/**
 * Created by caoshihong on 2016/10/21.
 *
 * 所有接口基础返回bean
 */

public class BaseResponseBean<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
