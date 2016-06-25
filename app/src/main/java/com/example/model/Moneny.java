package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/16.
 * 聚财币 积分 实体类
 */
public class Moneny implements Serializable{

    private int page;
    private int totlePage;
    private String insertDate;
    private int id;
    private double detailMoney;
    private int type;
    private String remark;

    private int Integeral;
    private int jucaiBills;


    public int getIntegeral() {
        return Integeral;
    }

    public void setIntegeral(int integeral) {
        Integeral = integeral;
    }

    public int getJucaiBills() {
        return jucaiBills;
    }

    public void setJucaiBills(int jucaiBills) {
        this.jucaiBills = jucaiBills;
    }

    public double getDetailMoney() {
        return detailMoney;
    }

    public void setDetailMoney(double detailMoney) {
        this.detailMoney = detailMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTotlePage() {
        return totlePage;
    }

    public void setTotlePage(int totlePage) {
        this.totlePage = totlePage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
