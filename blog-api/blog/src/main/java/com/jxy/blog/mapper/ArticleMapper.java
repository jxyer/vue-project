package com.jxy.blog.mapper;

import java.util.HashMap;
import java.util.List;
import com.jxy.blog.domain.Article;
import com.jxy.blog.domain.ArticleType;
import com.jxy.blog.domain.Page;
import org.springframework.stereotype.Repository;

/**
 * 文章Mapper接口
 * 
 * @author jxy
 * @date 2021-01-23
 */
@Repository
public interface ArticleMapper 
{
    /**
     * 查询文章
     * 
     * @param id 文章ID
     * @return 文章
     */
    public Article selectArticleById(Long id);

    /**
     * 统计文章标签的个数
     * @return
     */
    List<HashMap<String,Object>> selectArticleCategoryAll();

    List<Article> getHotArticle();

    List<Article> selectArticleByPage(Page pages);

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
     * 删除文章
     * 
     * @param id 文章ID
     * @return 结果
     */
    public int deleteArticleById(Long id);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleByIds(Long[] ids);

    /**
     * 批量删除文章标签
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleTypeByArticleIds(Long[] ids);
    
    /**
     * 批量新增文章标签
     * 
     * @param articleTypeList 文章标签列表
     * @return 结果
     */
    public int batchArticleType(List<ArticleType> articleTypeList);
    

    /**
     * 通过文章ID删除文章标签信息
     * 
     * @param id 文章ID
     * @return 结果
     */
    public int deleteArticleTypeByArticleId(Long id);
}
