package com.jxy.blog.domain;

import java.util.Date;

/**
 * Token对象 token
 * 
 * @author jxy
 * @date 2021-01-18
 */
public class Token extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long userid;

    /** $column.columnComment */
    private Date expiretime;

    private Date updatetime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setExpiretime(Date expiretime) 
    {
        this.expiretime = expiretime;
    }

    public Date getExpiretime() 
    {
        return expiretime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", userid=" + userid +
                ", expiretime=" + expiretime +
                ", updatetime=" + updatetime +
                ", token='" + token + '\'' +
                '}';
    }
}
