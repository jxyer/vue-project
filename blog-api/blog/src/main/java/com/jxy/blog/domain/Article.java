package com.jxy.blog.domain;

import java.util.Date;
import java.util.List;

/**
 * 文章对象 article
 * 
 * @author jxy
 * @date 2021-01-23
 */
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 文章标题 */
    private String title;

    /** 作者 */
    private Long userid;

    private User user;

    /** 概述 */
    private String summary;

    /** 观看人数 */
    private Long viewcount;

    /** 文章内容 */
    private String content;

    /** 所属类别 */
    private Long categoryid;

    private ArticleCategory articleCategory;

    private Date createtime;

    public Date getCreateTime() {
        return createtime;
    }

    public void setCreateTime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdateTime() {
        return updatetime;
    }

    public void setUpdateTime(Date updatetime) {
        this.updatetime = updatetime;
    }

    private Date updatetime;

    /** 文章标签信息 */
    private List<ArticleType> articleTypeList;

    /**
     * 用户评论
     */
    private List<UserComment> userComments;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }
    public void setViewcount(Long viewcount) 
    {
        this.viewcount = viewcount;
    }

    public Long getViewcount() 
    {
        return viewcount;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCategoryid(Long categoryid) 
    {
        this.categoryid = categoryid;
    }

    public Long getCategoryid() 
    {
        return categoryid;
    }

    public List<ArticleType> getArticleTypeList()
    {
        return articleTypeList;
    }

    public void setArticleTypeList(List<ArticleType> articleTypeList)
    {
        this.articleTypeList = articleTypeList;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userid=" + userid +
                ", user=" + user +
                ", summary='" + summary + '\'' +
                ", viewcount=" + viewcount +
                ", content='" + content + '\'' +
                ", categoryid=" + categoryid +
                ", articleCategory=" + articleCategory +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", articleTypeList=" + articleTypeList +
                ", userComments=" + userComments +
                '}';
    }

    public List<UserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<UserComment> userComments) {
        this.userComments = userComments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArticleCategory getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(ArticleCategory articleCategory) {
        this.articleCategory = articleCategory;
    }
}
