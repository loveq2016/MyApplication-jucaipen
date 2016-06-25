package com.example.utils;

import android.util.Log;

import org.xutils.http.RequestParams;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/6.
 */
public class NetUtils {


    public  static RequestParams sendHttpGet(String url, Map<String,Object> param){
        RequestParams p= new RequestParams(url);
        if(param!=null){
            for (Map.Entry<String,Object> entry : param.entrySet()){
                p.addParameter(entry.getKey(),entry.getValue());
            }
        }

        return  p;

    }


}
