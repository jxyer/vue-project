package com.jxy.blog.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户评论对象 user_comment
 * 
 * @author jxy
 * @date 2021-01-25
 */
public class UserComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 评论内容 */
    private String content;

    /** 文章id */
    private Long articleid;

    /** 用户id */
    private Long userid;
    private User user;

    /** 父用户id */
    private Long touserid;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private Date updateTime;

    private long parentid;

    public long getParentId() {
        return parentid;
    }

    public void setParentId(long parentid) {
        this.parentid = parentid;
    }

    public Date getCreateTime() {
        return createtime;
    }

    public void setCreateTime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setArticleid(Long articleid) 
    {
        this.articleid = articleid;
    }

    public Long getArticleid() 
    {
        return articleid;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setTouserid(Long touserid) 
    {
        this.touserid = touserid;
    }

    public Long getTouserid() 
    {
        return touserid;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", articleid=" + articleid +
                ", userid=" + userid +
                ", touserid=" + touserid +
                ", createtime=" + createtime +
                ", updateTime=" + updateTime +
                ", parentid=" + parentid +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
