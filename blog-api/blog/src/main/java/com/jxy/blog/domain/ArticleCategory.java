package com.jxy.blog.domain;

/**
 * 文章类型对象 article_category
 * 
 * @author jxy
 * @date 2021-01-23
 */
public class ArticleCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名字 */
    private String name;

    /** 图标 */
    private String image;

    private String remark;
    /**
     * 被引用的文章数量
     */
    private int articleNum;

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
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "ArticleCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", remark='" + remark + '\'' +
                ", articleNum=" + articleNum +
                '}';
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }

    public int getArticleNum() {
        return articleNum;
    }
}
