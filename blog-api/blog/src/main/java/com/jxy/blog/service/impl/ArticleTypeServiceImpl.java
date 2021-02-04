package com.jxy.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.Article;
import com.jxy.blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jxy.blog.mapper.ArticleTypeMapper;
import com.jxy.blog.domain.ArticleType;
import com.jxy.blog.service.IArticleTypeService;

/**
 * 文章标签Service业务层处理
 * 
 * @author jxy
 * @date 2021-01-23
 */
@Service
public class ArticleTypeServiceImpl implements IArticleTypeService 
{
    @Autowired
    private ArticleTypeMapper articleTypeMapper;
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查询文章标签
     * 
     * @param id 文章标签ID
     * @return 文章标签
     */
    @Override
    public ArticleType selectArticleTypeById(Long id)
    {
        return articleTypeMapper.selectArticleTypeById(id);
    }

    @Override
    public Result selectArticleByName(String typename) {
        List<ArticleType> articleTypes = articleTypeMapper.selectArticleByName(typename);
        List<Article> articles=new ArrayList<>();
        for(ArticleType articleType:articleTypes){
            Article article = articleMapper.selectArticleById(articleType.getArticleid());
            articles.add(article);
        }
        return Result.success(articles);
    }

    @Override
    public Result selectArticleTypeByName(String typename) {
        ArticleType articleType = articleTypeMapper.selectArticleTypeByName(typename);
        return Result.success(articleType);
    }


    @Override
    public Result selectArticleTypeAndNum() {
        List<HashMap> hashMaps = articleTypeMapper.selectArticleTypeAndNum();
        return Result.success(hashMaps);
    }

    /**
     * 查询文章标签列表
     * 
     * @param articleType 文章标签
     * @return 文章标签
     */
    @Override
    public List<ArticleType> selectArticleTypeList(ArticleType articleType)
    {
        return articleTypeMapper.selectArticleTypeList(articleType);
    }

    /**
     * 新增文章标签
     * 
     * @param articleType 文章标签
     * @return 结果
     */
    @Override
    public int insertArticleType(ArticleType articleType)
    {
        return articleTypeMapper.insertArticleType(articleType);
    }

    /**
     * 修改文章标签
     * 
     * @param articleType 文章标签
     * @return 结果
     */
    @Override
    public int updateArticleType(ArticleType articleType)
    {
        return articleTypeMapper.updateArticleType(articleType);
    }

    /**
     * 批量删除文章标签
     * 
     * @param ids 需要删除的文章标签ID
     * @return 结果
     */
    @Override
    public int deleteArticleTypeByIds(Long[] ids)
    {
        return articleTypeMapper.deleteArticleTypeByIds(ids);
    }

    /**
     * 删除文章标签信息
     * 
     * @param id 文章标签ID
     * @return 结果
     */
    @Override
    public int deleteArticleTypeById(Long id)
    {
        return articleTypeMapper.deleteArticleTypeById(id);
    }
}
