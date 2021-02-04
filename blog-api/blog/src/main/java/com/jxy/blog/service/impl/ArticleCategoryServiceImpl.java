package com.jxy.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.Article;
import com.jxy.blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jxy.blog.mapper.ArticleCategoryMapper;
import com.jxy.blog.domain.ArticleCategory;
import com.jxy.blog.service.IArticleCategoryService;

/**
 * 文章类型Service业务层处理
 * 
 * @author jxy
 * @date 2021-01-23
 */
@Service
public class ArticleCategoryServiceImpl implements IArticleCategoryService 
{
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    private ArticleMapper articleMapper;




    /**
     * 查询文章类型
     * 
     * @param id 文章类型ID
     * @return 文章类型
     */
    @Override
    public ArticleCategory selectArticleCategoryById(Long id)
    {
        return articleCategoryMapper.selectArticleCategoryById(id);
    }

    @Override
    public Result selectArticleCategoryAll() {
        List<HashMap<String,Object>> hashMaps = articleMapper.selectArticleCategoryAll();
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectArticleCategoryList(null);
        for (HashMap<String,Object> hashMap:hashMaps) {
            Long categoryId = Long.valueOf(hashMap.get("categoryId").toString());
            Integer articleNum = Integer.valueOf(hashMap.get("a").toString());
            for (ArticleCategory articleCategory:articleCategories) {
                if (categoryId==articleCategory.getId()){
                    System.out.println(articleNum);
                    articleCategory.setArticleNum(articleNum);
                }
            }
        }
        return Result.success(articleCategories);
    }

    /**
     * 查询文章类型列表
     * 
     * @param articleCategory 文章类型
     * @return 文章类型
     */
    @Override
    public List<ArticleCategory> selectArticleCategoryList(ArticleCategory articleCategory)
    {
        return articleCategoryMapper.selectArticleCategoryList(articleCategory);
    }

    /**
     * 新增文章类型
     * 
     * @param articleCategory 文章类型
     * @return 结果
     */
    @Override
    public int insertArticleCategory(ArticleCategory articleCategory)
    {
        return articleCategoryMapper.insertArticleCategory(articleCategory);
    }

    /**
     * 修改文章类型
     * 
     * @param articleCategory 文章类型
     * @return 结果
     */
    @Override
    public int updateArticleCategory(ArticleCategory articleCategory)
    {
        return articleCategoryMapper.updateArticleCategory(articleCategory);
    }

    /**
     * 批量删除文章类型
     * 
     * @param ids 需要删除的文章类型ID
     * @return 结果
     */
    @Override
    public int deleteArticleCategoryByIds(Long[] ids)
    {
        return articleCategoryMapper.deleteArticleCategoryByIds(ids);
    }

    /**
     * 删除文章类型信息
     * 
     * @param id 文章类型ID
     * @return 结果
     */
    @Override
    public int deleteArticleCategoryById(Long id)
    {
        return articleCategoryMapper.deleteArticleCategoryById(id);
    }
}
