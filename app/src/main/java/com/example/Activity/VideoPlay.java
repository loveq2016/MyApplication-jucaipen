package com.example.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.fragment.IntroFragment;
import com.example.adapter.ViewPagerAdapter;
import com.example.fragment.CommFragmnet;
import com.example.view.media.QkVideoView;
import com.qukan.playsdk.QkMediaPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/13.
 * <p/>
 * 视频回顾
 */
public class VideoPlay extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener, View.OnTouchListener {
//    private RadioButton button_intro;
//    private RadioButton button_commend;
//    private ViewPager vp_video;
//    private ViewPagerAdapter adapter;
//    private List<Fragment> list=new ArrayList<Fragment>();
//    private FragmentManager fm;
//    private Playintro playintro;
//    private PlayCommend playCommend;
//    private RadioGroup video_group;
//    private IntroFragment introFragment;
//    private CommFragmnet commFragmnet;

    private RadioGroup plant_group;
    private RadioButton intro_rad;
    private RadioButton comm_rad;
    private ViewPager plant_pager;
    private IntroFragment introFragment;//简介
    private CommFragmnet commFragmnet;//评论
    private ViewPagerAdapter adapter;
    private List<Fragment> list = new ArrayList<Fragment>();
    private ImageButton btn_back;
    private ImageButton btn_big;
    private ImageButton btn_share;
    private QkVideoView qk_video;
    private boolean isVISIBLE = false;
    private TextView tv_alltime;
    private TextView tv_nowtime;
    private boolean mBackPressed;
    private ProgressBar video_progressbar;
    private Map<String, Object> map = new HashMap<>();
    private String title;
    private int hit;
    private boolean isSpecial;
    private boolean isCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.ui_vide);
        setContentView(R.layout.fragmenttwo);


        init();
    }


    private void init() {
        String videoUrl = getIntent().getStringExtra("videoUrl");
        int id = getIntent().getIntExtra("id", -1);
        int classId = getIntent().getIntExtra("classId", -1);
        title = getIntent().getStringExtra("title");
        hit = getIntent().getIntExtra("hit", -1);
        isSpecial = getIntent().getBooleanExtra("isSpecial", false);
        isCharge = getIntent().getBooleanExtra("isCharge", false);

        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("hit", hit);
        bundle.putInt("classId", classId);
        bundle.putBoolean("isSpecial",isSpecial);


        qk_video = (QkVideoView) findViewById(R.id.qk_video);
        qk_video.setOnTouchListener(this);


        //播放视频
        if (videoUrl != null && videoUrl.length() > 0) {
            PlayVideo(videoUrl);
        }


        introFragment = new IntroFragment();
        introFragment.setArguments(bundle);
        commFragmnet = new CommFragmnet(id);
        list.add(introFragment);
        list.add(commFragmnet);


        plant_group = (RadioGroup) findViewById(R.id.plant_group);
        plant_group.setOnCheckedChangeListener(this);
        intro_rad = (RadioButton) findViewById(R.id.intro_rad);
        comm_rad = (RadioButton) findViewById(R.id.comm_rad);
        plant_pager = (ViewPager) findViewById(R.id.plant_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        plant_pager.setOnPageChangeListener(this);
        plant_pager.setAdapter(adapter);

        intro_rad.setChecked(true);
        tv_nowtime = (TextView) findViewById(R.id.tv_nowtime);
        tv_nowtime.setVisibility(View.GONE);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        tv_alltime = (TextView) findViewById(R.id.tv_alltime);
        tv_alltime.setVisibility(View.GONE);
        btn_back.setVisibility(View.GONE);
        btn_big = (ImageButton) findViewById(R.id.btn_big);
        btn_big.setVisibility(View.GONE);
        btn_share = (ImageButton) findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);
        btn_share.setVisibility(View.GONE);
        video_progressbar = (ProgressBar) findViewById(R.id.video_progressbar);
        video_progressbar.setVisibility(View.GONE);


    }


    private void PlayVideo(String videoUrl) {
        QkMediaPlayer.loadLibrariesOnce(null);
        QkMediaPlayer.native_profileBegin("libqkplayer.so");
        // qk_video.setMediaController(mMediaController);
        qk_video.setVideoPath(videoUrl);
        qk_video.start();

    }


    @Override
    public void onBackPressed() {
        mBackPressed = true;

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !qk_video.isBackgroundPlayEnabled()) {
            qk_video.stopPlayback();
            qk_video.release(true);
            qk_video.stopBackgroundPlay();
        } else {
            qk_video.enterBackground();
        }
        //  QkMediaPlayer.native_profileEnd();
    }

//    private void init() {
//        video_group= (RadioGroup) findViewById(R.id.video_group);
//        video_group.setOnCheckedChangeListener(this);
//        button_intro= (RadioButton) findViewById(R.id.button_intro);
//        button_commend= (RadioButton) findViewById(R.id.button_commend);
//        vp_video= (ViewPager) findViewById(R.id.vp_video);
//        fm=getSupportFragmentManager();
//        playintro=new Playintro();
//        playCommend=new PlayCommend();
//        list.add(playintro);
//        list.add(playCommend);
//        adapter=new ViewPagerAdapter(fm,list);
//        vp_video.setAdapter(adapter);
//        button_intro.setChecked(true);
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.intro_rad:
                plant_pager.setCurrentItem(0);
                break;
            case R.id.comm_rad:
                plant_pager.setCurrentItem(1);
                break;
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
                intro_rad.setChecked(true);
                break;
            case 1:
                comm_rad.setChecked(true);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.qk_video:

                break;
            case R.id.btn_back:
                this.finish();
                break;
            case R.id.btn_share:
                Toast.makeText(VideoPlay.this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void changeState(boolean isShow) {

        isVISIBLE = isShow;
        if (isShow) {
            btn_back.setVisibility(View.GONE);
            btn_big.setVisibility(View.GONE);
            btn_share.setVisibility(View.GONE);
            tv_alltime.setVisibility(View.GONE);
            tv_nowtime.setVisibility(View.GONE);
            video_progressbar.setVisibility(View.GONE);
        } else {
            btn_back.setVisibility(View.VISIBLE);
            btn_big.setVisibility(View.VISIBLE);
            btn_share.setVisibility(View.VISIBLE);
            tv_alltime.setVisibility(View.VISIBLE);
            tv_nowtime.setVisibility(View.VISIBLE);
            video_progressbar.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {
            //按下
            case MotionEvent.ACTION_DOWN:

                if (isVISIBLE == false) {
                    changeState(true);
                } else {
                    changeState(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            changeState(true);
                        }
                    }, 5000);
                }
                break;


        }
        return false;
    }
}
