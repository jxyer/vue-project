package com.jxy.blog.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jxy.blog.mapper.UserCommentMapper;
import com.jxy.blog.domain.UserComment;
import com.jxy.blog.service.IUserCommentService;

/**
 * 用户评论Service业务层处理
 * 
 * @author jxy
 * @date 2021-01-25
 */
@Service
public class UserCommentServiceImpl implements IUserCommentService 
{
    @Autowired
    private UserCommentMapper userCommentMapper;

    /**
     * 查询用户评论
     * 
     * @param id 用户评论ID
     * @return 用户评论
     */
    @Override
    public UserComment selectUserCommentById(Long id)
    {
        return userCommentMapper.selectUserCommentById(id);
    }

    /**
     * 查询用户评论列表
     * 
     * @param userComment 用户评论
     * @return 用户评论
     */
    @Override
    public List<UserComment> selectUserCommentList(UserComment userComment)
    {
        return userCommentMapper.selectUserCommentList(userComment);
    }

    /**
     * 新增用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    @Override
    public int insertUserComment(UserComment userComment)
    {
        return userCommentMapper.insertUserComment(userComment);
    }

    /**
     * 修改用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    @Override
    public int updateUserComment(UserComment userComment)
    {
        return userCommentMapper.updateUserComment(userComment);
    }

    /**
     * 批量删除用户评论
     * 
     * @param ids 需要删除的用户评论ID
     * @return 结果
     */
    @Override
    public int deleteUserCommentByIds(Long[] ids)
    {
        return userCommentMapper.deleteUserCommentByIds(ids);
    }

    /**
     * 删除用户评论信息
     * 
     * @param id 用户评论ID
     * @return 结果
     */
    @Override
    public int deleteUserCommentById(Long id)
    {
        return userCommentMapper.deleteUserCommentById(id);
    }
}
