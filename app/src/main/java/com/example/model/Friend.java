package com.example.model;

/**
 * Created by Administrator on 2016/6/17.
 * 邀请好友实体类
 */
public class Friend {
    //[{"page":1,"totlePage":1,"id":39,"recommderDate":"2016-06-20 08:59:57.643",
    // "recommder":"telTest","recommderPhone":null,"recommderImage":"http:20166209940.jpg"}]
    private int page;
    private int totlePage;
    private int id;
    private String recommderDate;
    private String recommder;
    private String recommderPhone;
    private String recommderImage;

    public void setId(int id) {
        this.id = id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRecommder(String recommder) {
        this.recommder = recommder;
    }

    public void setRecommderDate(String recommderDate) {
        this.recommderDate = recommderDate;
    }

    public void setRecommderImage(String recommderImage) {
        this.recommderImage = recommderImage;
    }

    public void setRecommderPhone(String recommderPhone) {
        this.recommderPhone = recommderPhone;
    }

    public void setTotlePage(int totlePage) {
        this.totlePage = totlePage;
    }

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public String getRecommder() {
        return recommder;
    }

    public String getRecommderDate() {
        return recommderDate;
    }

    public String getRecommderImage() {
        return recommderImage;
    }

    public String getRecommderPhone() {
        return recommderPhone;
    }

    public int getTotlePage() {
        return totlePage;
    }
}
