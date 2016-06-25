package com.example.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/2.
 */
public class Classify implements Serializable{
    private int id;
    private String name;

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
}
