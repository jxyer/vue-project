package com.jxy.blog.exceptions;

import com.jxy.blog.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    /**
     * token身份过期
     * @return
     */
    @ExceptionHandler(value = TokenException.class)
    @ResponseBody
    public Result tokenExpireException(){
        System.out.println("token异常");
        return Result.tokenExpireError();
    }
}
