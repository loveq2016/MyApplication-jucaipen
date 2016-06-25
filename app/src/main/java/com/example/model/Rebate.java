package com.example.model;

/**
 * Created by Administrator on 2016/6/23.
 * 最新榜单实体类
 */
public class Rebate {

    private int id;
    private String ninkName;
    private int contributeJucaiBills;
    private String userFace;

    private  boolean isLeavel;

    public boolean isLeavel() {
        return isLeavel;
    }

    public void setLeavel(boolean leavel) {
        isLeavel = leavel;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public int getContributeJucaiBills() {
        return contributeJucaiBills;
    }

    public void setContributeJucaiBills(int contributeJucaiBills) {
        this.contributeJucaiBills = contributeJucaiBills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNinkName() {
        return ninkName;
    }

    public void setNinkName(String ninkName) {
        this.ninkName = ninkName;
    }
}
