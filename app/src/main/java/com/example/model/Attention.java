package com.example.model;

/**
 * Created by Administrator on 2016/6/17.
 * <p/>
 * 我的关注实体类
 */
public class Attention {
    // [{"page":1,"totlePage":1,"id":62,"teacherImage":"http://img.jucaipen.com/jucjpg",
    // "nickName":"远清看市","isV":1,"notice":"主张攻守兼备，大波段的利润来自持股的耐心！",
    // "fans":151,"leavel":"CCTV产品讲师"}]

    private int page;
    private int totlePage;
    private int id;
    private String teacherImage;
    private String nickName;
    private int isV;
    private String notice;
    private int fans;
    private String leavel;
    private String startDate;
    private String endDate;
    private String introduce;


    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public String getLeavel() {
        return leavel;
    }

    public void setLeavel(String leavel) {
        this.leavel = leavel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTeacherImage() {
        return teacherImage;
    }

    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage;
    }

    public int getTotlePage() {
        return totlePage;
    }

    public void setTotlePage(int totlePage) {
        this.totlePage = totlePage;
    }
}
