package com.jxy.blog.controller;

import com.jxy.blog.common.Result;
import com.jxy.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @CrossOrigin
    @GetMapping("/api/getCurrentUserInfo")
    public Result getCurrentUserInfo(HttpServletRequest request){
        return userService.getCurrentUserInfo(request.getHeader("token"));
    }

    @GetMapping("/api/logout")
    @CrossOrigin
    public Result logout(HttpServletRequest request){
        return userService.logout(request.getHeader("token"));
    }
}
