package com.jxy.blog.service;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.User;

public interface ILoginService {

    Result login(User user);

    Result register(User user);
}
