package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/30.
 */
public class Hotspot implements Serializable {

    /**
     *当前页
     * */
    private int page;
    /**
     *总页数
     * */
    private  int pagecount;
    /**
     * id
     * */
    private  int id;
    /**
     * 标题cover
     * */
    private String title;
    /**
     * 封面图片(地址和数组两个类型)
     * */
    private String coverurl;
   // private byte[] cover;
    /**
     * 评论数
     * */
    private int comments;
    /**
     * 发布时间
     * */
    private String datetime;
    /**
     * 新闻来源
     * */
    private String news;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

//    public byte[] getCover() {
//        return cover;
//    }
//
//    public void setCover(byte[] cover) {
//        this.cover = cover;
//    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }



}
