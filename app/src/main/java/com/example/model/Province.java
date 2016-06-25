package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/6.
 */
public class Province implements Serializable {

    /**
     * pager : -1
     * totlePager : -1
     * id : 1
     * name : 北京市
     * ProSort : 1
     */

    private int pager;
    private int totlePager;
    private int id;
    private String name;
    private int ProSort;

    public int getPager() {
        return pager;
    }

    public void setPager(int pager) {
        this.pager = pager;
    }

    public int getTotlePager() {
        return totlePager;
    }

    public void setTotlePager(int totlePager) {
        this.totlePager = totlePager;
    }

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

    public int getProSort() {
        return ProSort;
    }

    public void setProSort(int ProSort) {
        this.ProSort = ProSort;
    }
}
