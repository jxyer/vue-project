package com.jxy.blog.common;

import org.springframework.util.StringUtils;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 3381388464375528105L;

    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String DATA = "data";

    public Result() {
    }

    public Result(int code, String msg) {
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    public Result(int code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if (!StringUtils.isEmpty(data)) {
            super.put(DATA, data);
        }
    }

    public static Result success(Object data){
        return Result.success("发送成功",data);
    }

    public static Result success(String msg){
        return Result.success(msg,null);
    }

    public static Result success(String msg, Object data){
        return new Result(ResultCode.SUCCESS,msg,data);
    }

    public static Result error(Object data){
        return Result.error("发送失败",data);
    }

    public static Result error(String msg){
        return Result.error(msg,null);
    }

    public static Result error(String msg, Object data){
        return new Result(ResultCode.ERROR,msg,data);
    }

    public static Result tokenExpireError(){
        return new Result(ResultCode.TOKEN,"用户身份过期,请重新登录");
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
