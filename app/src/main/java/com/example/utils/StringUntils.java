package com.example.utils;

/**
 * Created by jucaipen on 16/5/10.
 */
public class StringUntils {
    /*
    是否属于测试接口
     */
    private static boolean isTest = true;

    public static String getHostName() {
        if (isTest) {
            //返回测试主机名
            return "192.168.1.134:8080";
        } else {
            //返回上线主机名
            return "192.168.1.1:8080";

        }

    }


}
