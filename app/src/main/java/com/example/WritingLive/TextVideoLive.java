package com.example.WritingLive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.Activity.Login;
import com.example.adapter.ViewPagerAdapter;
import com.example.androidnetwork.R;
import com.example.utils.NetUtils;
import com.example.utils.StoreUtils;
import com.example.utils.StringUntils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/26.
 * <p/>
 * 文字直播  FragmentActivity
 */
public class TextVideoLive extends FragmentActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private RadioGroup tvlive_grp;
    private RadioButton rbt_tv;
    private RadioButton rbt_chat;
    private RadioButton rbt_look;
    // private RadioButton rbt_guard;
    private FrameLayout text_pager;
    private ImageView tv_back;

    private TextLive textLive;//文字直播
    private ChatRoom chatRoom;//悄悄话
    private ReadingPlate readingPlate;//浏览解盘
    private List<Fragment> list;
    private int id;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Map<String, Object> map = new HashMap<>();
    private int teacherId;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textvideo);

        init();
    }

    private void initFragment() {
        id = getIntent().getIntExtra("id", -1);
        teacherId = getIntent().getIntExtra("teacherId", -1);

        bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("teacherId", teacherId);
        //  whisperGuard = new WhisperGuard();
        list = new ArrayList<>();
        list.add(textLive);
        list.add(readingPlate);
        list.add(chatRoom);
        //  list.add(whisperGuard);

    }

    private void init() {
        int type = getIntent().getIntExtra("type", -1);
        if (type == 1) {

        }
        fm=getSupportFragmentManager();
        initFragment();
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        tvlive_grp = (RadioGroup) findViewById(R.id.tvlive_grp);
        tvlive_grp.setOnCheckedChangeListener(this);
        rbt_tv = (RadioButton) findViewById(R.id.rbt_tv);
        rbt_chat = (RadioButton) findViewById(R.id.rbt_chat);
        rbt_look = (RadioButton) findViewById(R.id.rbt_look);
        // rbt_guard = (RadioButton) findViewById(R.id.rbt_guard);
        text_pager = (FrameLayout) findViewById(R.id.text_pager);

       /* viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        text_pager.setAdapter(viewPagerAdapter);
        text_pager.setOnPageChangeListener(this);*/
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
        ft=fm.beginTransaction();
        switch (checkedId) {
            case R.id.rbt_tv:
                if (textLive==null){
                    textLive = new TextLive();
                }
                textLive.setArguments(bundle);
                ft.replace(R.id.text_pager,textLive).commit();
                break;
            case R.id.rbt_chat:
                //是否进入守护悄悄话
                intoGurdianRoom();
                break;
            case R.id.rbt_look:
                if(readingPlate==null){
                    readingPlate = new ReadingPlate();
                }
                readingPlate.setArguments(bundle);
                ft.replace(R.id.text_pager,readingPlate).commit();
                break;
//            case R.id.rbt_guard:
//                text_pager.setCurrentItem(3);
//                break;
            default:
                break;
        }
    }

    private void intoGurdianRoom() {
        int userId = StoreUtils.getUserInfo(this);
        if (userId <= 0) {
            Intent chat = new Intent();
            chat.setClass(this, Login.class);
            this.startActivity(chat);
        }
        ft=fm.beginTransaction();
        if(chatRoom==null){
            chatRoom = new ChatRoom();
        }
        chatRoom.setArguments(bundle);
        ft.replace(R.id.text_pager,chatRoom).commit();
        //是否已经成为守护者
    }

}
