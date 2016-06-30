package com.example.model;

/**
 * Created by Administrator on 2016/6/12.
 * <p/>
 * 文字直播 实体类
 */
public class TextVideo {
    private int id;
    private String title;
    private String teacherName;
    private String teacherFace;
    private int isV;
    private int attentNum;
    private String images;
    private String bodys;
    private int isLock;
    private String insertDate;
    private int teacherId;
    private int totpagler;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getBodys() {
        return bodys;
    }

    public void setBodys(String bodys) {
        this.bodys = bodys;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getAttentNum() {
        return attentNum;
    }

    public void setAttentNum(int attentNum) {
        this.attentNum = attentNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsV() {
        return isV;
    }

    public void setIsV(int isV) {
        this.isV = isV;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
