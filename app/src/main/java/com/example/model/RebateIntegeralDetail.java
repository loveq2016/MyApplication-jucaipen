package com.example.model;

import java.io.Serializable;

/*
* 我的返利实体类
* */
public class RebateIntegeralDetail implements Serializable {
    private int page;
    private int totlePage;
    private int id;
    private int userId;
    private int integralNum;
    private String insertDate;
    private String remark;
    private String type;
    private int fromId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(int integralNum) {
        this.integralNum = integralNum;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }
}
