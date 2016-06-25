package com.example.Live;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.reciver.MyReceiver;
import com.example.utils.JsonUtil;
import com.example.utils.MarqueeTextView;
import com.example.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/5/11.
 */
public class LiveRoom extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener {
    private RadioGroup room_group;
    private ViewPager room_viewpager;
    private InteractList interactList;//互动列表
    private NewList newList;//最新榜单
    private TeacherIdea teacherIdea;//老师观点
    private UserSeat userSeat;//用户席位
    private List<Fragment> list = new ArrayList<Fragment>();
    private RadioButton radio_interact, radio_seat, radio_idea, radio_news;
    private ViewPagerAdapter adapter;
    private MarqueeTextView user_round;
    private int teacherId;
    private ImageView video_finsh;
    private ImageButton video_share;
    private int liveId;
    private ImageButton iv_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_liveroom_main);
        init();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter recIntent=new IntentFilter(JPushInterface.ACTION_MESSAGE_RECEIVED);
        MyReceiver receiver=new MyReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                    String receiverMsg=intent.getExtras().getString(JPushInterface.EXTRA_MESSAGE);
                    //[{"sendId":48,"sendName":"学习找牛股","msg":"份饭","shenHe":0,"sendLeavel":0,"toName":null}]

                //解析聊天
                JsonUtil.getMsg(receiverMsg);
                    Toast.makeText(LiveRoom.this, "接受的"+receiverMsg, Toast.LENGTH_SHORT).show();
            }
        };

        this.registerReceiver(receiver,recIntent);
    }

    private void init() {
        teacherId=getIntent().getIntExtra("teacherId",-1);
        liveId=getIntent().getIntExtra("liveId",-1);

        Bundle bundle=new Bundle();
        bundle.putInt("teacherId",teacherId);
        bundle.putInt("liveId",liveId);

        interactList = new InteractList();
        interactList.setArguments(bundle);
        userSeat = new UserSeat();

        teacherIdea = new TeacherIdea();
        teacherIdea.setArguments(bundle);


        newList = new NewList();
        newList.setArguments(bundle);


        list.add(interactList);
        list.add(userSeat);
        list.add(teacherIdea);
        list.add(newList);

        room_group = (RadioGroup) findViewById(R.id.room_group);
        room_group.setOnCheckedChangeListener(this);
        video_share= (ImageButton) findViewById(R.id.video_share);
        video_share.setOnClickListener(this);
        iv_big= (ImageButton) findViewById(R.id.iv_big);
        iv_big.setOnClickListener(this);



        video_finsh= (ImageView) findViewById(R.id.video_finsh);
        video_finsh.setOnClickListener(this);


        user_round= (MarqueeTextView) findViewById(R.id.user_round);
        user_round.setText("加载图片时先查看缓存中时候存在该图片，如果存在则返回该图片，否则先加载载一个默认的占位图片");
        radio_interact = (RadioButton) findViewById(R.id.radio_interact);
        radio_seat = (RadioButton) findViewById(R.id.radio_seat);
        radio_idea = (RadioButton) findViewById(R.id.radio_idea);
        radio_news = (RadioButton) findViewById(R.id.radio_news);

        room_viewpager = (ViewPager) findViewById(R.id.room_viewpager);
        room_viewpager.setOnPageChangeListener(this);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        room_viewpager.setCurrentItem(0);
        room_viewpager.setAdapter(adapter);

    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radio_interact:
                room_viewpager.setCurrentItem(0, false);

                break;
            case R.id.radio_seat:
                room_viewpager.setCurrentItem(1, false);

                break;
            case R.id.radio_idea:
                room_viewpager.setCurrentItem(2, false);

                break;
            case R.id.radio_news:
                room_viewpager.setCurrentItem(3, false);

                break;
            case R.id.iv_big:
                Toast.makeText(LiveRoom.this, "全屏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_share:
                Toast.makeText(LiveRoom.this, "分享", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        switch (i) {
            case 0:
                room_group.check(R.id.radio_interact);
                break;
            case 1:
                room_group.check(R.id.radio_seat);
                break;
            case 2:
                room_group.check(R.id.radio_idea);
                break;
            case 3:
                room_group.check(R.id.radio_news);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_finsh:
                this.finish();
                break;
        }
    }
}
