package com.example.WritingLive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adapter.ViewPagerAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 * <p/>
 * 文字直播  FragmentActivity
 */
public class TextVideoLive extends FragmentActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private RadioGroup tvlive_grp;
    private RadioButton rbt_tv;
    private RadioButton rbt_chat;
    private RadioButton rbt_look;
   // private RadioButton rbt_guard;
    private ViewPager text_pager;
    private ImageView tv_back;

    private TextLive textLive;//文字直播
    private ChatRoom chatRoom;//聊天室
    private ReadingPlate readingPlate;//浏览解盘
   // private WhisperGuard whisperGuard;//守护悄悄话
    private List<Fragment> list;
    private ViewPagerAdapter viewPagerAdapter;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textvideo);

        init();
    }

    private void initFragment() {
        id=getIntent().getIntExtra("id",-1);

        Bundle bundle=new Bundle();
        bundle.putInt("id",id);

        textLive = new TextLive();
        textLive.setArguments(bundle);


        chatRoom = new ChatRoom();


        readingPlate = new ReadingPlate();
        readingPlate.setArguments(bundle);
      //  whisperGuard = new WhisperGuard();
        list = new ArrayList<>();
        list.add(textLive);
        list.add(chatRoom);
        list.add(readingPlate);
      //  list.add(whisperGuard);

    }

    private void init() {
        int type=getIntent().getIntExtra("type",-1);
        if (type==1){

        }
        initFragment();
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        tvlive_grp = (RadioGroup) findViewById(R.id.tvlive_grp);
        tvlive_grp.setOnCheckedChangeListener(this);
        rbt_tv = (RadioButton) findViewById(R.id.rbt_tv);
        rbt_chat = (RadioButton) findViewById(R.id.rbt_chat);
        rbt_look = (RadioButton) findViewById(R.id.rbt_look);
       // rbt_guard = (RadioButton) findViewById(R.id.rbt_guard);
        text_pager = (ViewPager) findViewById(R.id.text_pager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        text_pager.setAdapter(viewPagerAdapter);
        text_pager.setOnPageChangeListener(this);
        rbt_tv.setChecked(true);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbt_tv:
                text_pager.setCurrentItem(0);
                break;
            case R.id.rbt_chat:
                text_pager.setCurrentItem(1);
                break;
            case R.id.rbt_look:
                text_pager.setCurrentItem(2);
                break;
//            case R.id.rbt_guard:
//                text_pager.setCurrentItem(3);
//                break;
            default:
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
                tvlive_grp.check(R.id.rbt_tv);
                break;
            case 1:
                tvlive_grp.check(R.id.rbt_chat);
                break;
            case 2:
                tvlive_grp.check(R.id.rbt_look);
                break;
//            case 3:
//                tvlive_grp.check(R.id.rbt_guard);
//                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
