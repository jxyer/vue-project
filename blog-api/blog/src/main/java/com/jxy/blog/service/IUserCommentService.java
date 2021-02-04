package com.jxy.blog.service;

import java.util.List;
import com.jxy.blog.domain.UserComment;

/**
 * 用户评论Service接口
 * 
 * @author jxy
 * @date 2021-01-25
 */
public interface IUserCommentService 
{
    /**
     * 查询用户评论
     * 
     * @param id 用户评论ID
     * @return 用户评论
     */
    public UserComment selectUserCommentById(Long id);

    /**
     * 查询用户评论列表
     * 
     * @param userComment 用户评论
     * @return 用户评论集合
     */
    public List<UserComment> selectUserCommentList(UserComment userComment);

    /**
     * 新增用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    public int insertUserComment(UserComment userComment);

    /**
     * 修改用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    public int updateUserComment(UserComment userComment);

    /**
     * 批量删除用户评论
     * 
     * @param ids 需要删除的用户评论ID
     * @return 结果
     */
    public int deleteUserCommentByIds(Long[] ids);

    /**
     * 删除用户评论信息
     * 
     * @param id 用户评论ID
     * @return 结果
     */
    public int deleteUserCommentById(Long id);
}
