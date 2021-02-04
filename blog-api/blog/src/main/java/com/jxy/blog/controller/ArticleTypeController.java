package com.jxy.blog.controller;


import com.jxy.blog.common.Result;
import com.jxy.blog.domain.Article;
import com.jxy.blog.domain.ArticleCategory;
import com.jxy.blog.service.IArticleCategoryService;
import com.jxy.blog.service.IArticleService;
import com.jxy.blog.service.IArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleTypeController {

    @Autowired
    private IArticleCategoryService articleCategoryService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IArticleTypeService articleTypeService;


    @CrossOrigin
    @GetMapping("/api/getArticleType")
    public Result getArticleType(){
        return articleCategoryService.selectArticleCategoryAll();
    }

    @CrossOrigin
    @GetMapping("/api/getArticleById/{id}")
    public Result getArticleById(@PathVariable("id")Long id){
        Article article=new Article();
        article.setCategoryid(id);
        List<Article> articles = articleService.selectArticleList(article);
        return Result.success(articles);
    }

    @CrossOrigin
    @GetMapping("/api/getArticleTypeById/{id}")
    public Result getArticleTypeById(@PathVariable("id")Long id){
        ArticleCategory articleCategory = articleCategoryService.selectArticleCategoryById(id);
        return Result.success(articleCategory);
    }

    @CrossOrigin
    @GetMapping("/api/getLabel")
    public Result getLabel(){
        return articleTypeService.selectArticleTypeAndNum();
    }

    @CrossOrigin
    @GetMapping("/api/getArticleByName/{typename}")
    public Result getArticleByName(@PathVariable("typename")String typename){
        return articleTypeService.selectArticleByName(typename);
    }

    @CrossOrigin
    @GetMapping("/api/getArticleTypeByName/{typename}")
    public Result getArticleTypeByName(@PathVariable("typename")String typename){
        return articleTypeService.selectArticleTypeByName(typename);
    }

}
