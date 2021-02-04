package com.jxy.blog.controller;

import com.jxy.blog.common.Result;
import com.jxy.blog.domain.Page;
import com.jxy.blog.domain.UserComment;
import com.jxy.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @CrossOrigin
    @GetMapping("/api/getArticleInfo")
    public Result getArticleInfo(){
        return articleService.getArticleInfo();
    }
    @CrossOrigin
    @PostMapping("/api/addArticle")
    public Result addArticle(@RequestBody Map<String,Object> map, HttpServletRequest request){
        return articleService.addArticle(map,request.getHeader("token"));
    }
    @CrossOrigin
    @GetMapping("/api/getArticles/{page}/{num}")
    public Result getArticles(@PathVariable("page")int page,@PathVariable("num")int num){
        return articleService.getArticles(new Page(Page.getStartPage(page),num));
    }
    @CrossOrigin
    @GetMapping("/api/getCurrentArticle/{id}")
    public Result getArticleInfo(@PathVariable int id){
        return articleService.getCurrentArticle(id);
    }
    @CrossOrigin
    @PostMapping("/api/addComment")
    public Result addComment(@RequestBody UserComment userComment){
        return articleService.addComment(userComment);
    }
    @CrossOrigin
    @PutMapping("/api/updateComment/{id}")
    public Result updateComment(@PathVariable("id") long id){
        return articleService.updateComment(id);
    }
    @CrossOrigin
    @PutMapping("/api/updateView/{id}")
    public Result updateView(@PathVariable("id") long id){
        return articleService.updateView(id);
    }
    @CrossOrigin
    @GetMapping("/api/getHotLabel/{num}")
    public Result getHotLabel(@PathVariable("num")Integer num){
        if (num==0)return articleService.getHotLabel(null);
        return articleService.getHotLabel(num);
    }

    @CrossOrigin
    @GetMapping("/api/getHotArticle")
    public Result getHotArticle(){
        return articleService.getHotArticle();
    }
}
