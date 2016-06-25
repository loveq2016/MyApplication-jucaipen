package com.example.model;

/**
 * Created by Administrator on 2016/6/22.
 * 浏览解盘 实体类
 */
public class Plate {
    private int id;
    private String body;
    private String insertDate;
    private int isFree;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }
}
