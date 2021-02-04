package com.jxy.blog.service;

import java.util.List;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.ArticleCategory;

/**
 * 文章类型Service接口
 * 
 * @author jxy
 * @date 2021-01-23
 */
public interface IArticleCategoryService 
{
    /**
     * 查询文章类型
     * 
     * @param id 文章类型ID
     * @return 文章类型
     */
    public ArticleCategory selectArticleCategoryById(Long id);

    Result selectArticleCategoryAll();


    /**
     * 查询文章类型列表
     * 
     * @param articleCategory 文章类型
     * @return 文章类型集合
     */
    public List<ArticleCategory> selectArticleCategoryList(ArticleCategory articleCategory);

    /**
     * 新增文章类型
     * 
     * @param articleCategory 文章类型
     * @return 结果
     */
    public int insertArticleCategory(ArticleCategory articleCategory);

    /**
     * 修改文章类型
     * 
     * @param articleCategory 文章类型
     * @return 结果
     */
    public int updateArticleCategory(ArticleCategory articleCategory);

    /**
     * 批量删除文章类型
     * 
     * @param ids 需要删除的文章类型ID
     * @return 结果
     */
    public int deleteArticleCategoryByIds(Long[] ids);

    /**
     * 删除文章类型信息
     * 
     * @param id 文章类型ID
     * @return 结果
     */
    public int deleteArticleCategoryById(Long id);
}
