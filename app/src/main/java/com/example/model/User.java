package com.example.model;

import java.io.Serializable;

/**
 * @author ylf
 *
 *         用户
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {
    /**
     * 用户信息当前页
     */
    private int page;
    /**
     * 用户信息总页数
     */
    private int totlePage;
    /**
     * 用户年龄
     */
    private int age;

    /**
     * 用户id
     */
    private int id;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String trueName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * Email
     */
    private String email;
    /**
     * 用户头像
     */
    private String faceImage;
    /**
     * 手机号
     */
    private String mobileNum;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 家乡（省）id
     */
    private int homeProvince;
    /**
     * 家乡（市）id
     */
    private int homeCity;
    /**
     * 家乡（区）id
     */
    private int homeArea;
    /**
     * 所在省份id
     */
    private int localProvinceId;
    /**
     * 所在城市id
     */
    private int localCityId;
    /**
     * 所在区id
     */
    private int localAreaId;
    /**
     * 所在省份
     */
    private String localProvince;
    /**
     * 所在城市
     */
    private String localCity;
    /**
     * 所在区
     */
    private String localArea;
    /**
     * 个人描述
     */
    private String descript;
    /**
     * 注册时间
     */
    private String regDate;
    /**
     * 帐号状态（0正常 1锁定 4删除）
     */
    private int isLock;
    /**
     * 绑定微博id
     */
    private String weiBoId;
    /**
     * qq绑定id
     */
    private String qqId;
    /**
     * 微信绑定id
     */
    private String weiXinId;
    /**
     * 是否完成邮箱验证（0 未完成 1 已完成）
     */
    private int isChechEmail;
    /**
     * 是否完成手机验证（0 未完成 1已完成）
     */
    private int isChechMobile;
    /**
     * 注册ip
     */
    private String regIp;
    private int isFree;
    private String serverStartDate;
    private String serverEndDate;
    private int isStop;
    private int serverId;
    private int isRoomManager;
    private int buyProductId;
    private int reginFrom;
    private String headFace;



    public int getLocalProvinceId() {
        return localProvinceId;
    }

    public void setLocalProvinceId(int localProvinceId) {
        this.localProvinceId = localProvinceId;
    }

    public int getLocalCityId() {
        return localCityId;
    }

    public void setLocalCityId(int localCityId) {
        this.localCityId = localCityId;
    }


    public String getHeadFace() {
        return headFace;
    }

    public void setHeadFace(String headFace) {
        this.headFace = headFace;
    }

    public int getLocalAreaId() {
        return localAreaId;
    }

    public void setLocalAreaId(int localAreaId) {
        this.localAreaId = localAreaId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getNickName() {
        return nickName;
    }


    public int getReginFrom() {
        return reginFrom;
    }

    public void setReginFrom(int reginFrom) {
        this.reginFrom = reginFrom;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHomeProvince() {
        return homeProvince;
    }

    public void setHomeProvince(int homeProvince) {
        this.homeProvince = homeProvince;
    }

    public int getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(int homeCity) {
        this.homeCity = homeCity;
    }

    public int getHomeArea() {
        return homeArea;
    }

    public void setHomeArea(int homeArea) {
        this.homeArea = homeArea;
    }

    public String getLocalProvince() {
        return localProvince;
    }

    public void setLocalProvince(String localProvince) {
        this.localProvince = localProvince;
    }

    public String getLocalCity() {
        return localCity;
    }

    public void setLocalCity(String localCity) {
        this.localCity = localCity;
    }

    public String getLocalArea() {
        return localArea;
    }

    public void setLocalArea(String localArea) {
        this.localArea = localArea;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public String getWeiBoId() {
        return weiBoId;
    }

    public void setWeiBoId(String weiBoId) {
        this.weiBoId = weiBoId;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public String getWeiXinId() {
        return weiXinId;
    }

    public void setWeiXinId(String weiXinId) {
        this.weiXinId = weiXinId;
    }

    public int getIsChechEmail() {
        return isChechEmail;
    }

    public void setIsChechEmail(int isChechEmail) {
        this.isChechEmail = isChechEmail;
    }

    public int getIsChechMobile() {
        return isChechMobile;
    }

    public void setIsChechMobile(int isChechMobile) {
        this.isChechMobile = isChechMobile;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getServerStartDate() {
        return serverStartDate;
    }

    public void setServerStartDate(String serverStartDate) {
        this.serverStartDate = serverStartDate;
    }

    public String getServerEndDate() {
        return serverEndDate;
    }

    public void setServerEndDate(String serverEndDate) {
        this.serverEndDate = serverEndDate;
    }

    public int getIsStop() {
        return isStop;
    }

    public void setIsStop(int isStop) {
        this.isStop = isStop;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getIsRoomManager() {
        return isRoomManager;
    }

    public void setIsRoomManager(int isRoomManager) {
        this.isRoomManager = isRoomManager;
    }

    public int getBuyProductId() {
        return buyProductId;
    }

    public void setBuyProductId(int buyProductId) {
        this.buyProductId = buyProductId;
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


}
