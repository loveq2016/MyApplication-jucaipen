package com.example.Present;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adapter.ViewPagerAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 * <p/>
 * 我的礼品
 */
public class MyPresent extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    private FmyPresent fmyPresent;
    private Fsendgift fsendgift;
    private Freceivegift freceivegift;
    private List<Fragment> list;
    private String[] str;
    private RadioGroup present_group;
    private ViewPager present_pager;
    private RadioButton rbt_my;
    private RadioButton rbt_send;
    private RadioButton rbt_receive;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypresent);

        init();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        present_group = (RadioGroup) findViewById(R.id.present_group);
        present_group.setOnCheckedChangeListener(this);
        present_pager = (ViewPager) findViewById(R.id.present_pager);
        present_pager.setOnPageChangeListener(this);
        rbt_my = (RadioButton) findViewById(R.id.rbt_my);
        rbt_send = (RadioButton) findViewById(R.id.rbt_send);
        rbt_receive = (RadioButton) findViewById(R.id.rbt_receive);


        fmyPresent = new FmyPresent();
        fsendgift = new Fsendgift();
        freceivegift = new Freceivegift();
        list = new ArrayList<>();
        list.add(fmyPresent);
        list.add(fsendgift);
        list.add(freceivegift);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        present_pager.setAdapter(adapter);
        rbt_my.setChecked(true);
        present_pager.setCurrentItem(0);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbt_my:
                present_pager.setCurrentItem(0, false);
                break;
            case R.id.rbt_send:
                present_pager.setCurrentItem(1, false);
                break;
            case R.id.rbt_receive:
                present_pager.setCurrentItem(2, false);
                break;
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case 0:
                present_group.check(R.id.rbt_my);
                break;
            case 1:
                present_group.check(R.id.rbt_send);
                break;
            case 2:
                present_group.check(R.id.rbt_receive);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}































