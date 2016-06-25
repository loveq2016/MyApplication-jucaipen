package com.example.model;

/**
 * Created by Administrator on 2016/6/22.
 *
 * 直播 老师观点实体类
 */
public class TeacherDate {
    //id":795,"title":"证监会：要加强对投融资者的教育","desc":","insertDate":"2016-05-18 09:46:07.837","readNum":7,"isFree":0}]
    private int id;
    private String title;
    private String desc;
    private String insertDate;
    private int  readNum;
    private  int  isFree;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
