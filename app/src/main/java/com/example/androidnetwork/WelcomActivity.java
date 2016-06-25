package com.example.androidnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2016/6/16.
 */
public class WelcomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.approve);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(imageView);
        initView();
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
}
