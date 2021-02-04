package com.jxy.blog.domain;


public class Page {
    //第几页
    private int page;
    //一页展示多少数据
    private int num;

    public Page(int page, int num) {
        this.page = page;
        this.num = num;
    }

    public int getPage() {

        return page;
    }

    public static int getStartPage(int page){
        if (page==1){
            page=0;
        }else{
            page=9+(page-2)*5;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
