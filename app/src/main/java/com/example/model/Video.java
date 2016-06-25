package com.example.model;

/**
 * Created by Administrator on 2016/5/11.
 * 首页信息视频实体类
 */
public class Video {
    private int id;
    private String title;
    private String imageUrl;
    private int comms;
    private int hits;
    private String desc;
    private String videoUrl;
    private int classId;
    private int page;
    private int totlePage;
    private int playCount;
    private String videoDate;
    private  boolean isSpecial;
    private boolean isCharge;

    public boolean isCharge() {
        return isCharge;
    }

    public void setCharge(boolean charge) {
        isCharge = charge;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotlePage() {
        return totlePage;
    }

    public void setTotlePage(int totlePage) {
        this.totlePage = totlePage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComms() {
        return comms;
    }

    public void setComms(int comms) {
        this.comms = comms;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
