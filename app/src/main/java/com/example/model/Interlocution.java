package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/2.
 */
public class Interlocution implements Serializable{
    private int page;
    private int totlePage;
    private int askId;
    private String trueName;
    private String insertDate;
    private String askBodys;
    private String headFace;
    private int isReply;
    private int isPay;
    private String answerBody;


    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public String getAskBodys() {
        return askBodys;
    }

    public void setAskBodys(String askBodys) {
        this.askBodys = askBodys;
    }

    public int getAskId() {
        return askId;
    }

    public void setAskId(int askId) {
        this.askId = askId;
    }

    public String getHeadFace() {
        return headFace;
    }

    public void setHeadFace(String headFace) {
        this.headFace = headFace;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public int getIsReply() {
        return isReply;
    }

    public void setIsReply(int isReply) {
        this.isReply = isReply;
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

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
}
