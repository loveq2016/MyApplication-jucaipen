package com.example.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.AlreadyPay.BuyAnswer;
import com.example.AlreadyPay.BuyLogPoint;
import com.example.AlreadyPay.BuyVideo;
import com.example.AlreadyPay.BuyVideoPoint;
import com.example.AlreadyPay.BuySpecial;
import com.example.adapter.ViewPagerAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 * <p/>
 * 已购买
 */
public class Alreadybuy extends FragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private BuyVideo buyVideo;
    private BuyAnswer buyAnswer;
    private BuyLogPoint buyLogPoint;
    private BuySpecial buySpecial;
    private BuyVideoPoint videoPoint;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager buy_viewpager;
    private ViewPagerAdapter adapter;
    private RadioGroup buy_grop;
    private HorizontalScrollView buy_horizontal;
    private String[] str;
    private ImageView buy_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alreadybuy);

        init();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init() {

        str = new String[5];
        str[0] = "视频";
        str[1] = "专辑";
        str[2] = "日志观点";
        str[3] = "直播观点";
        str[4] = "问答";

        buyVideo = new BuyVideo();
        buySpecial = new BuySpecial();
        buyLogPoint = new BuyLogPoint();
        videoPoint = new BuyVideoPoint();
        buyAnswer = new BuyAnswer();

        buy_viewpager = (ViewPager) findViewById(R.id.buy_viewpager);
        buy_viewpager.setOnPageChangeListener(this);
        buy_horizontal = (HorizontalScrollView) findViewById(R.id.buy_horizontal);
        buy_grop = (RadioGroup) findViewById(R.id.buy_grop);
        buy_image = (ImageView) findViewById(R.id.buy_image);
        buy_image.setOnClickListener(this);


        for (int i = 0; i < str.length; i++) {
            RadioButton button = new RadioButton(this);
            button.setText(str[i]);
            button.setPadding(72, 20, 72, 20);
            button.setTextSize(20);
            button.setButtonDrawable(android.R.color.transparent);
            button.setBackground(getResources().getDrawable(R.drawable.radiobutton_selecter));
            button.setId(i);
            buy_grop.addView(button, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        buy_viewpager.setAdapter(adapter);
        initbutton();
        buy_grop.setOnCheckedChangeListener(this);
        RadioButton rb = (RadioButton) buy_grop.getChildAt(0);
        rb.setChecked(true);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initbutton() {

        fragmentList.add(buyVideo);
        fragmentList.add(buySpecial);
        fragmentList.add(buyLogPoint);
        fragmentList.add(videoPoint);
        fragmentList.add(buyAnswer);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton button = (RadioButton) buy_grop.getChildAt(position);
        if (button != null) {
            button.setChecked(true);
            int x = (int) button.getX();
            int y = (int) button.getY();
            buy_horizontal.smoothScrollTo(x, y);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton rb = (RadioButton) group.getChildAt(i);
            if (i == checkedId) {
                rb.setTextColor(Color.RED);
            } else {
                rb.setTextColor(Color.BLACK);
            }
        }
        buy_viewpager.setCurrentItem(checkedId);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_image:
                this.finish();
                break;

        }
    }
}
