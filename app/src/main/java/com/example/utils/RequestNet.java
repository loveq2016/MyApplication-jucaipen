//
//package com.jucaipen.utils;
//
//import okhttp3.MediaType;
//
//import java.io.IOException;
//import java.util.Map;
//
//import android.app.Activity;
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class RequestNet {
//    private static StringBuffer buffer;
//    private static final MediaType contentJson = MediaType
//            .parse("application/json; charset=utf-8");
//
//    /**
//     * @param url
//     * @param map
//     * @return okhttp get方法
//     */
//    public static String get(String url, Map<String, String> map,
//                             Activity activity) {
//
////        if (NetWorks(false, activity) == false) {
////            return "{ret_code:" + (-1) + "}";
////        }
//        OkHttpClient client = new OkHttpClient();
//
//        buffer = new StringBuffer(url);
//        if (map != null && map.size() > 0) {
//            buffer.append("?");
//            for (Map.Entry<String, String> p : map.entrySet()) {
//                buffer.append(p.getKey());
//                buffer.append("=");
//                buffer.append(p.getValue());
//                buffer.append("&");
//            }
//            buffer.replace(buffer.length() - 1, buffer.length(), "");
//        }
//        Request request = new Request.Builder().url(buffer.toString()).build();
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            //return response.body().string();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            } else {
//                throw new IOException("Unexpected code " + response);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * @param url
//     * @param param
//     * @return post请求 参数是Map集合
//     */
//    public static String postParam(String url, Map<String, String> param,
//                                   Activity activity) {
//        // 判断网络状态
////		if (NetWorks(false, activity) == false) {
////
////			return "{ret_code:" + (-1) + "}";
////		}
//
//        OkHttpClient client = new OkHttpClient();
//
//        FormBody body = null;
//        if (param != null && param.size() > 0) {
//            for (Map.Entry<String, String> p : param.entrySet()) {
//                body = new FormBody.Builder().add(p.getKey(), p.getValue())
//                        .build();
//            }
//        }
//        Request request = new Request.Builder().url(url).post(body).build();
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            } else {
//                throw new IOException("Unexpected code " + response);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * @param url
//     * @param json
//     * @return Post请求 参数Json
//     */
//    public static String postJson(String url, String json, Activity activity) {
//        if (NetWorks(false, activity) == false) {
//
//            return "{ret_code:" + (-1) + "}";
//        }
//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(contentJson, json);
//        Request request = new Request.Builder().url(url).post(body).build();
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            } else {
//                throw new IOException("Unexpected code " + response);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * @param bool
//     * @return 判断网络是否可用
//     */
//    public static boolean NetWorks(boolean bool, Activity activity) {
//        Context context = activity.getApplicationContext();
//        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
//        ConnectivityManager connectivityManager = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager == null) {
//            return false;
//        } else {
//            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
//            if (networkInfo != null && networkInfo.length > 0) {
//                for (int i = 0; i < networkInfo.length; i++) {
//                    // 判断当前网络状态是否为连接状态
//                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//
//}
