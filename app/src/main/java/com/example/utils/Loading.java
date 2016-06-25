package com.example.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/24.
 * 动画效果
 */
public class Loading {
    public static Animation loadanntation(Context context, View view) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.set);
        view.startAnimation(animation);
        return animation;
    }

}
