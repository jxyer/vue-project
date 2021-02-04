package com.jxy.blog.service;

import java.util.HashMap;
import java.util.List;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.ArticleType;

/**
 * 文章标签Service接口
 * 
 * @author jxy
 * @date 2021-01-23
 */
public interface IArticleTypeService 
{
    /**
     * 查询文章标签
     * 
     * @param id 文章标签ID
     * @return 文章标签
     */
    public ArticleType selectArticleTypeById(Long id);

    /**
     * 查询文章:根据标签名字
     * @return
     */
    Result selectArticleByName(String typename);

    /**
     * 查询标签:根据标签名字
     * @param typename
     * @return
     */
    Result selectArticleTypeByName(String typename);

    /**
     * 查询文章标签和标签引用的数量
     * @return
     */
    Result selectArticleTypeAndNum();

    /**
     * 查询文章标签列表
     * 
     * @param articleType 文章标签
     * @return 文章标签集合
     */
    public List<ArticleType> selectArticleTypeList(ArticleType articleType);

    /**
     * 新增文章标签
     * 
     * @param articleType 文章标签
     * @return 结果
     */
    public int insertArticleType(ArticleType articleType);

    /**
     * 修改文章标签
     * 
     * @param articleType 文章标签
     * @return 结果
     */
    public int updateArticleType(ArticleType articleType);

    /**
     * 批量删除文章标签
     * 
     * @param ids 需要删除的文章标签ID
     * @return 结果
     */
    public int deleteArticleTypeByIds(Long[] ids);

    /**
     * 删除文章标签信息
     * 
     * @param id 文章标签ID
     * @return 结果
     */
    public int deleteArticleTypeById(Long id);
}
