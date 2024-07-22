package com.music.common.lang;

import lombok.Data;
import java.io.Serializable;

@Data
public class PageResult implements Serializable {
    private int code;
    private String msg;
    private Object data;
    private int total;
    public static PageResult succ(Object data) {
        return succ(200,"操作成功",data,0);
    }

    public static PageResult succ(Object data, int total) {
        return succ(200,"操作成功",data,total);
    }

    public static PageResult succ(String msg,Object data, int total){
        return succ(200,msg,data,total);
    }

    public static PageResult succ(int code, String msg, Object data ,int total) {
        PageResult r = new PageResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setTotal(total);
        return r;
    }

    public static PageResult fail(String msg) {
        return fail(400,msg,null,0);
    }

    public static PageResult fail(String msg, int total) {
        return fail(400,msg,null,total);
    }

    public static PageResult fail(String msg, Object data, int total) {
        return fail(400,msg,data,total);
    }

    public static PageResult fail(int code, String msg, Object data, int total) {
        PageResult r = new PageResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setTotal(total);
        return r;
    }
}
