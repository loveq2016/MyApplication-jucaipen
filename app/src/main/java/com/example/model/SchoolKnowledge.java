package com.example.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 *
 *
 * //[{"id":1,"bigType":"证劵","item":
 // [{"sId":2,"smallType":"初级教程"},{"sId":19,"smallType":"中级教程"},{"sId":20,"smallType":"高级教程"}]}]
 *
 *  学院 教程分类
 */
public class SchoolKnowledge implements Serializable {
    private  int id;
    private  int color;
    private  int bigId;
    private  int smallId;
    private  String bigType;
    private List<SmallType> smallType;


    public int getBigId() {
        return bigId;
    }

    public void setBigId(int bigId) {
        this.bigId = bigId;
    }

    public String getBigType() {
        return bigType;
    }

    public void setBigType(String bigType) {
        this.bigType = bigType;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmallId() {
        return smallId;
    }

    public void setSmallId(int smallId) {
        this.smallId = smallId;
    }

    public List<SmallType> getSmallType() {
        return smallType;
    }

    public void setSmallType(List<SmallType> smallType) {
        this.smallType = smallType;
    }
}
