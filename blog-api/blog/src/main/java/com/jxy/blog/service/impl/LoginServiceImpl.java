package com.jxy.blog.service.impl;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.Token;
import com.jxy.blog.domain.User;
import com.jxy.blog.mapper.UserMapper;
import com.jxy.blog.service.ILoginService;
import com.jxy.blog.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ITokenService tokenService;

    @Override
    public Result login(User user) {
        List<User> users = userMapper.selectUserList(user);
        if (users.size()==1){
            User user1 = users.get(0);
            tokenService.deleteToken(user1.getId());
            Token token = tokenService.createToken(user1.getId());
            Result result=Result.success(user1);
            result.put("token",token);
            return result;
        }else if(users.size()>1)return Result.error("登录失败");
        return Result.error("用户或密码错误");
    }

    @Override
    public Result register(User user) {
        Integer count= userMapper.selectUserByName(user.getName());
        System.out.println(count);
        if (count==0) {
            userMapper.insertUser(user);
            return Result.success("注册成功");
        }else{
            return Result.error("该用户已存在");
        }

    }
}
