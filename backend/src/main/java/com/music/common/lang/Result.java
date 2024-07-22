package com.music.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;
    public long total;
    public static Result succ(Object data) {
        return succ(200,"操作成功",data,0);
    }

    public static Result succ(Object data, long total) {
        return succ(200,"操作成功",data,total);
    }

    public static Result succ(String msg,Object data){
        return succ(200,msg,data,0);
    }

    public static Result succ(String msg,Object data,long total){
        return succ(200,msg,data,total);
    }

    public static Result succ(int code, String msg, Object data,long total) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setTotal(total);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400,msg,null,0);
    }

    public static Result fail(String msg, Object data) {

        return fail(400,msg,data,0);
    }

    public static Result fail(String msg, long total) {
        return fail(400,msg,null,total);
    }

    public static Result fail(String msg, Object data, long total) {

        return fail(400,msg,data,total);
    }

    public static Result fail(int code, String msg, Object data,long total) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setTotal(total);
        return r;
    }
}
