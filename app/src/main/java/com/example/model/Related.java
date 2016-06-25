package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/3.
 */
public class Related implements Serializable {


    /**
     * id : 7222
     * title : 标题
     * from : 来源
     * comms : 评论数
     * insertDate : 发表时间
     * image : 封面图片
     */

    private int id;
    private String title;
    private String from;
    private int comms;
    private String insertDate;
    private String image;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getComms() {
        return comms;
    }

    public void setComms(int comms) {
        this.comms = comms;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
