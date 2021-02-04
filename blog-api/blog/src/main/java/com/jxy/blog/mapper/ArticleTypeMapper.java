package com.jxy.blog.mapper;

import java.util.HashMap;
import java.util.List;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.ArticleType;
import org.springframework.stereotype.Repository;

/**
 * 文章标签Mapper接口
 * 
 * @author jxy
 * @date 2021-01-23
 */
@Repository
public interface ArticleTypeMapper 
{

    /**
     * 获取最热标签
     * @param num 获取前几个
     * @return
     */
    List<ArticleType> getHotLabel(Integer num);

    /**
     * 查询文章标签和标签引用的数量
     * @return
     */
    List<HashMap> selectArticleTypeAndNum();

    /**
     * 查询标签:根据标签名字
     * @param typename
     * @return
     */
    ArticleType selectArticleTypeByName(String typename);

    /**
     * 查询文章标签:根据标签名字
     * @return
     */
    List<ArticleType> selectArticleByName(String typename);

    /**
     * 查询文章标签
     * 
     * @param id 文章标签ID
     * @return 文章标签
     */
    public ArticleType selectArticleTypeById(Long id);

    /**
     * 查询去重数据
     * @return
     */
    public List<ArticleType> selectArticleTypeName();

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
     * 删除文章标签
     * 
     * @param id 文章标签ID
     * @return 结果
     */
    public int deleteArticleTypeById(Long id);

    /**
     * 批量删除文章标签
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleTypeByIds(Long[] ids);
}
