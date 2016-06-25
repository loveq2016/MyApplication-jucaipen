package com.example.application;


import android.app.Application;

import com.example.utils.UILImageLoader;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.jpush.android.api.JPushInterface;

public class InitImageLoder extends Application {

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        x.Ext.init(this);
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder().build();  //设置主题
        FunctionConfig function = new FunctionConfig.Builder().build();  //配置功能
        ImageLoader loader = new UILImageLoader();  //配置ImageLoader
        CoreConfig config = new CoreConfig.Builder(this, loader, theme).setFunctionConfig(function).build();
        GalleryFinal.init(config);

        //初始化极光推送
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);


    }

}
