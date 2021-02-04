package com.jxy.blog.service.impl;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.Token;
import com.jxy.blog.domain.User;
import com.jxy.blog.mapper.TokenMapper;
import com.jxy.blog.mapper.UserMapper;
import com.jxy.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Result getCurrentUserInfo(String token) {
        Token token1 = tokenMapper.selectTokenById(token);
        User user = userMapper.selectUserById(token1.getUserid());
        Result success = Result.success(user);
        success.put("token",token);
        return success;
    }

    @Override
    public Result logout(String token) {
        Token token1 = tokenMapper.selectTokenById(token);
        tokenMapper.deleteTokenById(token1.getId());
        return Result.success("退出成功");
    }
}
