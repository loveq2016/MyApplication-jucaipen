package com.example.androidnetwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.ApkUtil;
import com.example.utils.KeepNetwork;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.finalteam.toolsfinal.StringUtils;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2016/6/16.
 */
public class WelcomActivity extends Activity {
    private  static  final  String GET_VERSIONINFO="http://192.168.1.134:8080/Jucaipen/jucaipen/updateversion";
    private  static final String DOWNLOAD_APK="http://192.168.1.134:8080/Jucaipen/jucaipen/download";
    private String downApk = "http://" + StringUntils.setHost()
            + ":8080/AccumulateWealth/jucaipen/downLoadApk";
    private ProgressDialog pDialog;
    private  Map<String,String> para=new HashMap<>();
    private Dialog updateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.approve);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(imageView);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("正在更新...");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        int code=getMyVersionCode();
        //检测新版本
         checkVisonCode(code);
    }


    public  int getMyVersionCode(){
        ApkUtil.getInstance().initParams(this);
        return  ApkUtil.getInstance().getCurrentVersionCode();
    }

    private void downNewVersionUI(final String apkUrl) {
        //下载最新的APK

        showUpdateDialog(apkUrl);


//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle("软件更新");
//        builder.setMessage("版本更新");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//               // downLoad(apkUrl);
//                new downLoad().execute(apkUrl);
//            }
//        });
//        Dialog dialog=builder.create();
//        dialog.show();



    }


    public  class  downLoad extends AsyncTask<String,Void,byte[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }

        @Override
        protected byte[] doInBackground(String... params) {
            para.put("fileName",params[0]);
            byte bs []=KeepNetwork.networkConnection(DOWNLOAD_APK,para);
            return bs;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            pDialog.cancel();
            inistApk(bytes);
        }
    }


    public void downNewVersionTask(String apkUrl){
        pDialog.show();



//        map.clear();
//        map.put("fileName",apkUrl);
//        RequestParams p=NetUtils.sendHttpGet(DOWNLOAD_APK,map);
//        p.setAutoResume(true);
//        p.setSaveFilePath(Environment.getExternalStorageDirectory().getPath()+"/聚财盆.apk");
//        x.http().post(p, new Callback.ProgressCallback<File>() {
//            @Override
//            public void onWaiting() {
//                Log.i("111", "onWaiting: ");
//
//            }
//
//            @Override
//            public void onStarted() {
//                Log.i("111", "onStarted: ");
//
//            }
//
//            @Override
//            public void onLoading(long total, long current, boolean isDownloading) {
//                Log.i("111", "onLoading: "+"   "+total+"   "+total+"   "+isDownloading);
//
//            }
//
//            @Override
//            public void onSuccess(File result) {
//                pDialog.cancel();
//                if(result.exists()){
//                    inistApk(result);
//                    Toast.makeText(WelcomActivity.this, ""+result.getName(), Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(WelcomActivity.this, "not fond ", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.i("111", "onError: "+ex.getMessage());
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }


    public void inistApk(byte[] bs){
            // 将下载的APK文件保存在SD卡中
           File file = Environment.getExternalStorageDirectory();
           FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(file, "聚财盆.apk"));
                fos.write(bs, 0, bs.length);
                String apkPath = file.getAbsolutePath() + "/" + file.getName();
                // 判断APK文件路径是否为空
                if (apkPath != null) {
                    // 安装APK文件
                    Uri uri = Uri.fromFile(new File(apkPath));
                    Intent install = new Intent(Intent.ACTION_VIEW);
                    install.setDataAndType(uri,
                            "application/vnd.android.package-archive");
                    startActivity(install);
                    // 打开安装好的APK
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if(fos!=null){
                        fos.flush();
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    private void checkVisonCode(final int code) {
        RequestParams p= NetUtils.sendHttpGet(GET_VERSIONINFO,null);
        x.http().get(p, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if(result!=null&&result.length()>0){
                    try {
                        JSONObject object=new JSONObject(result);
                        int ret_code=object.getInt("ret_code");
                        if(ret_code==0){
                            int versionCode=object.getInt("versionCode");
                            if(code<versionCode){
                                //下载最新版本
                                String apkUrl=object.getString("apkUrl");
                                downNewVersionUI(apkUrl);
                            }else {
                                initView();
                            }
                        }else {
                            initView();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent view = new Intent();
                view.setClass(WelcomActivity.this,MainActivity.class);
                startActivity(view);
                WelcomActivity.this.finish();
            }
        },2000);
    }

    /**
     * 显示是否更新新版本的对话框
     */
    public void showUpdateDialog(final String apkUrl) {
        updateDialog=new Dialog(this);
        View view= LayoutInflater.from(this).inflate(R.layout.prompt_alertdialog,null);
        updateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.setContentView(view);
        TextView tv_title = (TextView) updateDialog.findViewById(R.id.up_title);
        tv_title.setText("版本更新");
        TextView tv_content = (TextView) updateDialog.findViewById(R.id.up_details);
        tv_content.setMovementMethod(new ScrollingMovementMethod());
        tv_content
                .setText("是否更新新版本？新版本的具有如下特性：1.是否更新新版本？新版本的具有如下特性 2.是否更新新版本？新版本的具有如下特性 3.是否更新新版本？新版本的具有如下特性 4.是否更新新版本？新版本的具有如下特性  ");
        final TextView tv_sure = (TextView) updateDialog.findViewById(R.id.up_sure);
        tv_sure.setText("更新");
        updateDialog.show();
        tv_sure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               updateDialog.cancel();
                new downLoad().execute(apkUrl);
            }
        });

    }



}
