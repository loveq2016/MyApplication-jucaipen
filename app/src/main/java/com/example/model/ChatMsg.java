package com.example.model;

/**
 * Created by Administrator on 2016/6/25.
 * 聊天实体类
 */
public class ChatMsg {


    //[{"sendId":48,"sendName":"学习找牛股","msg":"份饭","shenHe":0,"sendLeavel":0,"toName":null}]
    private int sendId;
    private String sendName;
    private String msg;
    private int shenHe;
    private int sendLeavel;
    private String toName;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getSendLeavel() {
        return sendLeavel;
    }

    public void setSendLeavel(int sendLeavel) {
        this.sendLeavel = sendLeavel;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getShenHe() {
        return shenHe;
    }

    public void setShenHe(int shenHe) {
        this.shenHe = shenHe;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }
}
