package com.example.model;

/**
 * Created by Administrator on 2016/6/14.
 * 点播 视频评论实体类
 */
public class Comments {
    private int id;
    private String insertDate;
    private int reptCount;
    private String body;
    private String userName;
    private String headFace;
    private int userLeavel;
    private int goods;

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeadFace() {
        return headFace;
    }

    public void setHeadFace(String headFace) {
        this.headFace = headFace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public int getReptCount() {
        return reptCount;
    }

    public void setReptCount(int reptCount) {
        this.reptCount = reptCount;
    }

    public int getUserLeavel() {
        return userLeavel;
    }

    public void setUserLeavel(int userLeavel) {
        this.userLeavel = userLeavel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
