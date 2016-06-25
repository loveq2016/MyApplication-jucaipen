package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/2.
 */
public class Press implements Serializable {
    private String title;
    private int pager;
    private int totpager;
    private int id;
    private int comms;
    private String imageThumb;
    private String insertDate;
    private String from;
    private String imagUrl;
    private String body;
    private int readNum;
    private int goods;
    private String keyWord;
    private String shareUrl;
    private String teacherFace;
    private String teacherName;


    public String getTeacherFace() {
        return teacherFace;
    }

    public void setTeacherFace(String teacherFace) {
        this.teacherFace = teacherFace;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getComms() {
        return comms;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public void setComms(int comms) {
        this.comms = comms;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public int getPager() {
        return pager;
    }

    public void setPager(int pager) {
        this.pager = pager;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotpager() {
        return totpager;
    }

    public void setTotpager(int totpager) {
        this.totpager = totpager;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
