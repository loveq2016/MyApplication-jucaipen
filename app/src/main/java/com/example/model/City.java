package com.example.model;

/**
 * Created by Administrator on 2016/6/13.
 */
public class City {
    //[{"pager":-1,"totlePager":-1,"id":1,"name":"北京市","ProSort":1}]
    private int pager;
    private int totlePager;
    private int id;
    private String name;
    private int ProSort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPager() {
        return pager;
    }

    public void setPager(int pager) {
        this.pager = pager;
    }

    public int getProSort() {
        return ProSort;
    }

    public void setProSort(int proSort) {
        ProSort = proSort;
    }

    public int getTotlePager() {
        return totlePager;
    }

    public void setTotlePager(int totlePager) {
        this.totlePager = totlePager;
    }
}
