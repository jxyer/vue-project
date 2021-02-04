package com.jxy.blog.mapper;

import com.jxy.blog.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 【用户表】Mapper接口
 * 
 * @author jxy
 * @date 2021-01-18
 */
@Repository
public interface UserMapper 
{
    public User selectUserById(Long id);
    public int selectUserByName(String name);

    public List<User> selectUserList(User user);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUserById(Long id);

    public int deleteUserByIds(Long[] ids);
}
