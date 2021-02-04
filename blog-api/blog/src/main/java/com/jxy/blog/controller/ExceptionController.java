package com.jxy.blog.controller;

import com.jxy.blog.common.Result;
import com.jxy.blog.exceptions.TokenException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExceptionController{

    @RequestMapping("/tokenExpiredException")
    public Result expiredException(HttpServletRequest request) throws TokenException {
        if (request.getAttribute("tokenExpiredException") instanceof TokenException){
            throw new TokenException();
        }else{
            return Result.tokenExpireError();
        }
    }

}
