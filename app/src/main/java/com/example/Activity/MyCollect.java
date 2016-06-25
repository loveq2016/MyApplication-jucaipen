package com.example.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.androidnetwork.R;
import com.example.fragment.CollectVideo;
import com.example.fragment.Collectknowledge;

/**
 * Created by Administrator on 2016/5/19.
 * <p/>
 * 我的收藏
 */
public class MyCollect extends FragmentActivity implements View.OnClickListener {

    private Context context = MyCollect.this;
    private Collectknowledge knowledge;
    private CollectVideo video;
    private ImageButton collect_finish;
    private RadioButton my_video;
    private RadioButton my_knowledge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycollect);
        init();
        select(0);
    }

    private void select(int i) {
        FragmentManager fm = getSupportFragmentManager();
        //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //先隐藏 Fragment
        switch (i) {
            case 0:
                if (video == null) {
                    video = new CollectVideo();
                    ft.add(R.id.collect_frame, video).commit();
                } else {
                    ft.show(video).commit();
                }
                break;
            case 1:
                if (knowledge == null) {
                    knowledge = new Collectknowledge();
                    ft.add(R.id.collect_frame, knowledge).commit();
                } else {
                    ft.show(knowledge).commit();
                }
                break;
            default:
                break;
        }
    }

    private void hidtFragment(FragmentTransaction ft) {
        if (video != null) {
            ft.hide(video);
        }
        if (knowledge != null) {
            ft.hide(knowledge);
        }

    }

    private void init() {

        collect_finish = (ImageButton) findViewById(R.id.collect_finish);
        my_video = (RadioButton) findViewById(R.id.my_video);
        my_knowledge = (RadioButton) findViewById(R.id.my_knowledge);
        my_video.setOnClickListener(this);
        my_knowledge.setOnClickListener(this);
        collect_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collect_finish:
                this.finish();
                break;
            case R.id.my_video:
                select(0);
                break;
            case R.id.my_knowledge:
                select(1);
                break;
            default:
                break;
        }
    }
}
























