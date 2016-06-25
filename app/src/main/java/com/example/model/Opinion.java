package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/31.
 */
public class Opinion implements Serializable {
    private int page;
    private int totpager;
    private int id;
    private String insertdate;
    private String title;
    private String bodys;
    private int hits;
    private int goods;
    private int teacherId;
    private String nickname;
    private String level;
    private String headface;
    private int isv;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBodys() {
        return bodys;
    }

    public void setBodys(String bodys) {
        this.bodys = bodys;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public String getHeadface() {
        return headface;
    }

    public void setHeadface(String headface) {
        this.headface = headface;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(String insertdate) {
        this.insertdate = insertdate;
    }

    public int getIsv() {
        return isv;
    }

    public void setIsv(int isv) {
        this.isv = isv;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
}
