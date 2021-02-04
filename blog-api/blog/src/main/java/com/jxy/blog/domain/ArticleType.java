package com.jxy.blog.domain;

/**
 * 文章标签对象 article_type
 * 
 * @author jxy
 * @date 2021-01-23
 */
public class ArticleType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标签名字 */
    private String typename;

    /** 文章id */
    private Long articleid;

    private String image;

    //标签引用的数量
    private int num;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTypename(String typename) 
    {
        this.typename = typename;
    }

    public String getTypename() 
    {
        return typename;
    }
    public void setArticleid(Long articleid) 
    {
        this.articleid = articleid;
    }

    public Long getArticleid() 
    {
        return articleid;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
                "id=" + id +
                ", typename='" + typename + '\'' +
                ", articleid=" + articleid +
                ", image='" + image + '\'' +
                ", num=" + num +
                '}';
    }
}
