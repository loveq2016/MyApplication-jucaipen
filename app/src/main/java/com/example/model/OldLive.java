package com.example.model;

/**
 * Created by Administrator on 2016/6/22.
 *
 * wangqi适配实体类
 */
public class OldLive {
    //{"id":427,"title":"投资互动《第二期5.19》","imageUrl":"http37.jpg","videoDate":0,"hits":17,"isSpecial":false}
    private int id;
    private String title;
    private String imageUrl;
    private int videoDate;
    private int hits;
    private boolean isSpecial;
    private String videoUrl;
    private int classId;
    private boolean isCharge;


    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public boolean isCharge() {
        return isCharge;
    }

    public void setCharge(boolean charge) {
        isCharge = charge;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(int videoDate) {
        this.videoDate = videoDate;
    }
}
