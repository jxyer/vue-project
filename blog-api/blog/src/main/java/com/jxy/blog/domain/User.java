package com.jxy.blog.domain;

import java.util.Date;
/**
 * 【User】对象 user
 * 
 * @author jxy
 * @date 2021-01-18
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private String name;

    /** $column.columnComment */
    private String password;

    /** $column.columnComment */
    private Long deleted;

    /** $column.columnComment */
    private Date lasttime;

    /** $column.columnComment */
    private String phone;

    private Date createtime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setDeleted(Long deleted) 
    {
        this.deleted = deleted;
    }

    public Long getDeleted() 
    {
        return deleted;
    }
    public void setLasttime(Date lasttime) 
    {
        this.lasttime = lasttime;
    }

    public Date getLasttime() 
    {
        return lasttime;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", deleted=" + deleted +
                ", lasttime=" + lasttime +
                ", phone='" + phone + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
