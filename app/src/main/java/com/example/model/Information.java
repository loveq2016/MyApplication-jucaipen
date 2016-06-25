package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/4.
 */
public class Information implements Serializable {

    /**
     * nickName : 用户昵称
     * account : 账号
     * sex : 性别
     * trueName : 姓名
     * birthday : 生日
     * localCity : 所在地
     * investType : 投资风格
     * descript : 简介
     * faceImage : 头像
     */

    private String nickName;
    private String account;
    private String sex;
    private String trueName;
    private String birthday;
    private String localCity;
    private String investType;
    private String descript;
    private String faceImage;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocalCity() {
        return localCity;
    }

    public void setLocalCity(String localCity) {
        this.localCity = localCity;
    }

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }
}
