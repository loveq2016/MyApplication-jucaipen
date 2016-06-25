package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/3.
 */
public class Discuss implements Serializable {

    /**
     * id : 46
     * headFace : 用户头像
     * nickName : 用户昵称
     * insertDate : 评论时间
     * bodys : 评论内容
     * respCount : 回复数
     */

    private int id;
    private String headFace;
    private String nickName;
    private String insertDate;
    private String bodys;
    private int respCount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadFace() {
        return headFace;
    }

    public void setHeadFace(String headFace) {
        this.headFace = headFace;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public int getRespCount() {
        return respCount;
    }

    public void setRespCount(int respCount) {
        this.respCount = respCount;
    }
}
