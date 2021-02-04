package com.jxy.blog.service;

import com.jxy.blog.common.Result;

public interface IUserService {


    Result getCurrentUserInfo(String token);

    Result logout(String token);
}
