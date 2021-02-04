package com.jxy.blog.controller;


import com.jxy.blog.common.Result;
import com.jxy.blog.domain.User;
import com.jxy.blog.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    ILoginService loginService;

    @PostMapping("/api/login")
    @CrossOrigin
    public Result login(@RequestBody User user){
        System.out.println(user);
        return loginService.login(user);
    }

    @PostMapping("/api/register")
    @CrossOrigin
    public Result register(@RequestBody User user){
        user.setCreatetime(new Date());
        user.setDeleted(0L);
        System.out.println(user);
        return loginService.register(user);
    }



}
