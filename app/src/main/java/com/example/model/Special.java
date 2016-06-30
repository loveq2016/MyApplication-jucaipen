package com.example.model;

/**
 * Created by Administrator on 2016/6/29.
 */
public class Special {

    /**
     * page : 0
     * totlePage : 0
     * id : 428
     * title : 投资互动《第一期5.20》
     * imageUrl : http://img.jucaipen.com/jucaipenStudy/2016/5/24/2016524174932.jpg
     * desc : 投资互动《第一期5.20》
     * videoUrl :
     * classId : 1
     * playCount : 11
     * videoDate : 2016-05-24 17:49:29.823
     * isSpecial : false
     * isCharge : false
     */

    private int page;
    private int totlePage;
    private int id;
    private String title;
    private String imageUrl;
    private String desc;
    private String videoUrl;
    private int classId;
    private int playCount;
    private String videoDate;
    private boolean isSpecial;
    private boolean isCharge;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public boolean isIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public boolean isIsCharge() {
        return isCharge;
    }

    public void setIsCharge(boolean isCharge) {
        this.isCharge = isCharge;
    }
}
