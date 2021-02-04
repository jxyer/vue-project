package com.jxy.blog.config;

import com.jxy.blog.domain.Token;
import com.jxy.blog.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final String[] noLoginUrl = {"http://localhost:8888/api/login"
            , "http://localhost:8888/api/register"
            , "http://localhost:8888/tokenExpiredException",
            "http://localhost:8888/error"};
    @Autowired
    private ITokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURL().toString();
        System.out.println(requestURL);
        for (String url : noLoginUrl) {
            if (url.equals(requestURL)) return true;
        }
        String token = request.getHeader("token");
        System.out.println("token: " + token);
        Token token1 = tokenService.getToken(token);
        //没有token
        if (token1 == null) {
            request.getRequestDispatcher("/tokenExpiredException").forward(request,response);
            return false;
        }
        //身份过期
        if (new Date().getTime()>token1.getExpiretime().getTime()) {
            request.getRequestDispatcher("/tokenExpiredException").forward(request,response);
            return false;
        }
        return true;
    }
}
