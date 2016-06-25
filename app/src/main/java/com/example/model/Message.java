package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/4.
 */
public class Message implements Serializable{
   private int id;
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
