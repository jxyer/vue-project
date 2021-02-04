package com.jxy.blog.service;

import java.util.List;
import java.util.Map;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.Article;
import com.jxy.blog.domain.Page;
import com.jxy.blog.domain.UserComment;

/**
 * 文章Service接口
 * 
 * @author jxy
 * @date 2021-01-23
 */
public interface IArticleService 
{
    /**
     * 查询文章
     * 
     * @param id 文章ID
     * @return 文章
     */
    public Article selectArticleById(Long id);

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章ID
     * @return 结果
     */
    public int deleteArticleByIds(Long[] ids);

    /**
     * 删除文章信息
     * 
     * @param id 文章ID
     * @return 结果
     */
    public int deleteArticleById(Long id);

    Result getArticleInfo();

    Result addArticle(Map<String, Object> map, String token);

    Result getCurrentArticle(int id);

    Result addComment(UserComment userComment);

    Result updateComment(long id);

    Result updateView(long id);

    Result getArticles(Page page);

    Result getHotLabel(Integer num);

    Result getHotArticle();
}
