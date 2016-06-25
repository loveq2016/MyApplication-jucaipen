package com.example.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/20.
 * <p/>
 * 画圆
 */
public class DrawView extends View {


    //声明画笔
    Paint paint = null;

    public DrawView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取画笔，初始化画笔
        paint = new Paint();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = new Paint();
    }

    /*public DrawView(Context context) {
        super(context);

        *//*----------------------------------------------
         * 部分书籍中把Paint paint=new Paint();放在onDraw方法中，建议
         * 尽量不要把初始化画笔放在onDraw方法中，这是因为：onDraw经常会运行
         * 到的，不要在里面new对象，越少越好，不然很浪费内存
         *--------------------------------------------*//*
        //获取画笔，初始化画笔
        paint = new Paint();
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        //设置画布背景为白色
        canvas.drawColor(Color.WHITE);

        //设置画笔为红色
        paint.setColor(Color.RED);
        //使用当前画笔绘制一个左上角坐标为80,20，右下角坐标为360,180的矩形
        canvas.drawRect(80, 20, 360, 180, paint);

        //设置画笔为红色
        paint.setColor(Color.GREEN);
        //使用当前画笔绘制一个圆心坐标为220,100，半径为60的圆
        canvas.drawCircle(220, 100, 60, paint);
    }


}
