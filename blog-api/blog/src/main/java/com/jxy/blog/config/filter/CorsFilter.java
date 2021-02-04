package com.jxy.blog.config.filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CorsFilter")
public class CorsFilter implements Filter {
    private static final String AccessControlAllowOrigin="*";//允许跨域
    private static final String AccessControlAllowCredentials="true";//允许开放请求
    private static final String AccessControlAllowHeaders="*";//可以使用所有请求头
    private static final String AccessControlAllowMethods="*";//允许所有方法



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(1);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("允许跨域");
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin",AccessControlAllowOrigin);
        response.setHeader("Access-Control-Allow-Credentials",AccessControlAllowCredentials);
        response.setHeader("Access-Control-Allow-Headers",AccessControlAllowHeaders);
        response.setHeader("Access-Control-Allow-Methods",AccessControlAllowMethods);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
