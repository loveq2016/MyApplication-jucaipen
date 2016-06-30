package com.example.model;

/**
 * Created by Administrator on 2016/6/23.
 * <p/>
 * 问答详情实体类
 */
public class QuestionBean {
    //{"userFace":"http://img.jucaipen.com/jucaipenUpload/2016/3/7/20163717857.jpg",
    // "userNickName":"Fengerous","userLeavel":1,"askDate":"2016-06-04 16:42:50.923",
    // "askBody":"hello","isFree":0}
    private String userFace;
    private String userNickName;
    private int userLeavel;
    private String askDate;
    private String askBody;
    private int isFree;
    private int type;


    private String teacherFace;
    private String teacherNickName;
    private String teacherLeavel;
    private String answerDate;
    private String answerBody;


    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    public String getTeacherFace() {
        return teacherFace;
    }

    public void setTeacherFace(String teacherFace) {
        this.teacherFace = teacherFace;
    }

    public String getTeacherLeavel() {
        return teacherLeavel;
    }

    public void setTeacherLeavel(String teacherLeavel) {
        this.teacherLeavel = teacherLeavel;
    }

    public String getTeacherNickName() {
        return teacherNickName;
    }

    public void setTeacherNickName(String teacherNickName) {
        this.teacherNickName = teacherNickName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAskBody() {
        return askBody;
    }

    public void setAskBody(String askBody) {
        this.askBody = askBody;
    }

    public String getAskDate() {
        return askDate;
    }

    public void setAskDate(String askDate) {
        this.askDate = askDate;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public int getUserLeavel() {
        return userLeavel;
    }

    public void setUserLeavel(int userLeavel) {
        this.userLeavel = userLeavel;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
