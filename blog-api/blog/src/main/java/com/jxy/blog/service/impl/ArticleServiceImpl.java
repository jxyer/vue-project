package com.jxy.blog.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jxy.blog.common.Result;
import com.jxy.blog.common.TypeConversion;
import com.jxy.blog.domain.*;
import com.jxy.blog.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.jxy.blog.service.IArticleService;

/**
 * 文章Service业务层处理
 *
 * @author jxy
 * @date 2021-01-23
 */
@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTypeMapper articleType;

    @Autowired
    private ArticleCategoryMapper articleCategory;

    /**
     * 查询文章
     *
     * @param id 文章ID
     * @return 文章
     */
    @Override
    public Article selectArticleById(Long id) {
        return articleMapper.selectArticleById(id);
    }

    /**
     * 查询文章列表
     *
     * @param article 文章
     * @return 文章
     */
    @Override
    public List<Article> selectArticleList(Article article) {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 新增文章
     *
     * @param article 文章
     * @return 结果
     */
    @Transactional
    @Override
    public int insertArticle(Article article) {
        int rows = articleMapper.insertArticle(article);
        insertArticleType(article);
        return rows;
    }

    /**
     * 修改文章
     *
     * @param article 文章
     * @return 结果
     */
    @Transactional
    @Override
    public int updateArticle(Article article) {
        articleMapper.deleteArticleTypeByArticleId(article.getId());
        insertArticleType(article);
        return articleMapper.updateArticle(article);
    }

    /**
     * 批量删除文章
     *
     * @param ids 需要删除的文章ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteArticleByIds(Long[] ids) {
        articleMapper.deleteArticleTypeByArticleIds(ids);
        return articleMapper.deleteArticleByIds(ids);
    }

    /**
     * 删除文章信息
     *
     * @param id 文章ID
     * @return 结果
     */
    @Override
    public int deleteArticleById(Long id) {
        articleMapper.deleteArticleTypeByArticleId(id);
        return articleMapper.deleteArticleById(id);
    }

    /**
     * 获取文章信息
     *
     * @return
     */
    @Override
    public Result getArticleInfo() {
        List<ArticleCategory> articleCategories = articleCategory.selectArticleCategoryList(null);
        List<ArticleType> articleTypes = articleType.selectArticleTypeName();
        if (articleCategories == null || articleTypes == null) {
            return Result.error("数据获取失败");
        }
        if (articleCategories.size() == 0 || articleTypes.size() == 0)
            return Result.error("暂时没有数据");
        Result result = Result.success("成功");
        result.put("articleCategories", articleCategories);
        result.put("articleTypes", articleTypes);
        return result;
    }

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Result addArticle(Map<String, Object> map, String token) {
        Integer articleCategoryId = (Integer) map.get("ArticleCategory");
        Token token1 = tokenMapper.selectTokenById(token);
        System.out.println(map.get("Article"));
        Article article = null;
        try {

            article = (Article) TypeConversion.ObjToBean(map.get("Article"), Article.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        article.setCreateTime(new Date(new Date().getTime()));
        article.setUserid(token1.getUserid());
        article.setUpdateTime(article.getCreateTime());
        article.setCategoryid(articleCategoryId.longValue());
        this.articleMapper.insertArticle(article);
        //获取刚才的文章
        List<Article> articles = this.articleMapper.selectArticleList(article);
        if (article == null && articles.size() == 0) {
            return Result.error("该文章刚刚被删除了");
        } else {
            List<String> articleTypes = (ArrayList<String>) map.get("ArticleTypes");
            for (String s : articleTypes) {
                ArticleType articleType = new ArticleType();
                articleType.setArticleid(articles.get(articles.size() - 1).getId());
                articleType.setTypename(s);
                this.articleType.insertArticleType(articleType);
            }
            Result result = Result.success("发布成功");
            result.put("article_id", articles.get(articles.size() - 1).getId());
            return result;
        }

    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCommentMapper userCommentMapper;

    @Override
    public Result getCurrentArticle(int id) {
        Article article = articleMapper.selectArticleById((long) id);
        System.out.println("Article:" + article);
        User user = userMapper.selectUserById(article.getUserid());
        System.out.println("user:" + user);
        List<UserComment> userComments = userCommentMapper.selectUserCommentListByArticleId(article.getId());
        ArticleCategory articleCategory = this.articleCategory.selectArticleCategoryById(article.getCategoryid());
        Result result = Result.success("查询成功");
        result.put("article", article);
        result.put("user", user);
        result.put("userComments", userComments);
        result.put("articleCategory", articleCategory);
        return result;
    }

    @Override
    public Result addComment(UserComment userComment) {
        System.out.println(userComment);
        if (userComment.getUserid() <= 0) return Result.error("未知用户");
        if (userComment.getContent() == null || userComment.getContent().equals("")) return Result.error("请输入内容");
        int i = userCommentMapper.insertUserComment(userComment);
        Result result = Result.success("评论成功");
        result.put("id", userComment.getId());
        return result;
    }

    @Override
    public Result updateComment(long id) {
        List<UserComment> userComments = userCommentMapper.selectUserCommentListByArticleId(id);
        return Result.success(userComments.size());
    }

    @Override
    public Result updateView(long id) {
        Article article = articleMapper.selectArticleById(id);
        if(article==null)return Result.error("文章不存在");
        if (article.getViewcount() == null) {
            article.setViewcount(1L);
        } else {
            article.setViewcount(article.getViewcount() + 1L);
        }
        articleMapper.updateArticle(article);
        return Result.success(article.getViewcount());
    }

    @Override
    public Result getArticles(Page page) {
        if (page == null) return Result.success("没有数据了");
        List<Article> articles = articleMapper.selectArticleByPage(page);
        List<Article> temp = new ArrayList<>();
        for (Article article : articles) {
            Article article1 = articleMapper.selectArticleById(article.getId());
            System.out.println(article1);
            User user = userMapper.selectUserById(article1.getUserid());
            article1.setUser(user);
            temp.add(article1);
        }
        return Result.success(temp);
    }

    @Override
    public Result getHotLabel(Integer num) {
        return Result.success(articleType.getHotLabel(num));
    }

    @Override
    public Result getHotArticle() {
        return Result.success(articleMapper.getHotArticle());
    }

    /**
     * 新增文章标签信息
     *
     * @param article 文章对象
     */
    public void insertArticleType(Article article) {
        List<ArticleType> articleTypeList = article.getArticleTypeList();
        Long id = article.getId();
        if (articleTypeList == null) {
            List<ArticleType> list = new ArrayList<ArticleType>();
            for (ArticleType articleType : articleTypeList) {
                articleType.setArticleid(id);
                list.add(articleType);
            }
            if (list.size() > 0) {
                articleMapper.batchArticleType(list);
            }
        }
    }
}
