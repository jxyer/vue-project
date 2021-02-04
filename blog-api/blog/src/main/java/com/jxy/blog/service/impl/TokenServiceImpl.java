package com.jxy.blog.service.impl;

import com.jxy.blog.domain.Token;
import com.jxy.blog.mapper.TokenMapper;
import com.jxy.blog.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TokenServiceImpl implements ITokenService {

    private static final long expireTime=3600*12*1000;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Token getToken(String token) {
       return tokenMapper.selectTokenById(token);
    }


    @Override
    public Token createToken(long id) {
        long nowDate=new Date().getTime();
        long expireDate=nowDate+expireTime;//获取到期时间
        Token token=new Token();
        token.setUserid(id);
        token.setToken(generateToken());
        token.setUpdatetime(new Date(nowDate));
        token.setExpiretime(new Date(expireDate));
        tokenMapper.insertToken(token);
        return token;
    }

    /**
     *  生成token
     * @return
     */
    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public void expireToken(long id) {

    }

    @Override
    public void deleteToken(long userId) {
        tokenMapper.deleteTokenById(userId);
    }
}
