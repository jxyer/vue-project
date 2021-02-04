package com.jxy.blog.service;

import com.jxy.blog.domain.Token;

public interface ITokenService {

    Token getToken(String token);

    Token createToken(long id);

    void expireToken(long id);

    void deleteToken(long userId);

}
