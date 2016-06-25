package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/30.
 */
public class Famous implements Serializable{
    private int page;
    private int id;
    private String ninkName;
    private String level;
    private String notice;
    private int isv;
    private String headFace;
    private int fans;
    private int totpager;
    private String hoby;


    public String getHoby() {
        return hoby;
    }

    public void setHoby(String hoby) {
        this.hoby = hoby;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNinkName() {
        return ninkName;
    }

    public void setNinkName(String ninkName) {
        this.ninkName = ninkName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotpager() {
        return totpager;
    }

    public void setTotpager(int totpager) {
        this.totpager = totpager;
    }

    public String getHeadFace() {
        return headFace;
    }

    public void setHeadFace(String headFace) {
        this.headFace = headFace;
    }
}
