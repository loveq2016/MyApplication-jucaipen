package com.example.Activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.fragment.IntroFragment;
import com.example.adapter.ViewPagerAdapter;
import com.example.fragment.CommFragmnet;
import com.example.view.media.MyMediaController;
import com.example.view.media.QkVideoView;
import com.qukan.playsdk.IMediaPlayer;
import com.qukan.playsdk.QkMediaPlayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/13.
 * <p/>
 * 视频回顾
 */
public class VideoPlay extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener,
        View.OnClickListener, View.OnTouchListener {
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
    private SeekBar video_progressbar;
    private Map<String, Object> map = new HashMap<>();
    private String title;
    private int hit;
    private  RelativeLayout rl_control;
    private boolean isSpecial;
    private boolean isCharge;
    private ImageView iv_paly;
    private View view_video;

    private  RelativeLayout video_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenttwo);

        init();
    }


    @Override
    protected void onPause() {
        super.onPause();
        qk_video.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        qk_video.resume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        qk_video.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changeState(true);
            }
        },5000);
        qk_video.setKeepScreenOn(true);
    }

    private void init() {
        rl_control= (RelativeLayout) findViewById(R.id.rl_control);
        iv_paly= (ImageView) findViewById(R.id.iv_paly);
        String videoUrl = getIntent().getStringExtra("videoUrl");
        int id = getIntent().getIntExtra("id", -1);
        int classId = getIntent().getIntExtra("classId", -1);
        title = getIntent().getStringExtra("title");
        hit = getIntent().getIntExtra("hit", -1);
        video_lay= (RelativeLayout) findViewById(R.id.video_lay);
        isSpecial = getIntent().getBooleanExtra("isSpecial", false);
        isCharge = getIntent().getBooleanExtra("isCharge", false);

        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("hit", hit);
        bundle.putInt("classId", classId);
        bundle.putBoolean("isSpecial", isSpecial);


        qk_video = (QkVideoView) findViewById(R.id.qk_video);
        qk_video.setOnTouchListener(this);
        iv_paly.setOnClickListener(this);
        //播放视频
        if (videoUrl != null && videoUrl.length() > 0) {
            PlayVideo(videoUrl);
        }


        introFragment = new IntroFragment();
        introFragment.setArguments(bundle);
        bundle.putInt("id", id);
        commFragmnet = new CommFragmnet();
        commFragmnet.setArguments(bundle);
        list.add(introFragment);
        list.add(commFragmnet);


        plant_group = (RadioGroup) findViewById(R.id.plant_group);
        plant_group.setOnCheckedChangeListener(this);
        view_video = findViewById(R.id.view_videos);
        intro_rad = (RadioButton) findViewById(R.id.intro_rad);
        comm_rad = (RadioButton) findViewById(R.id.comm_rad);
        plant_pager = (ViewPager) findViewById(R.id.plant_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        plant_pager.setOnPageChangeListener(this);
        plant_pager.setAdapter(adapter);


        intro_rad.setChecked(true);
        tv_nowtime = (TextView) findViewById(R.id.tv_nowtime);




        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        tv_alltime = (TextView) findViewById(R.id.tv_alltime);
        btn_big = (ImageButton) findViewById(R.id.btn_big);
        btn_big.setOnClickListener(this);
        btn_share = (ImageButton) findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);
        video_progressbar = (SeekBar) findViewById(R.id.video_progressbar);
        video_progressbar.setThumbOffset(1);
        video_progressbar.setMax(1000);
        qk_video.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                long totlePro = iMediaPlayer.getDuration();
                tv_nowtime.setText(generateTime(totlePro));
                iMediaPlayer.setOnBufferingUpdateListener(new IMediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                        // c       d
                        //  ?      100
                        int totle= qk_video.getDuration();
                        int buff=qk_video.getBufferPercentage();
                        int current=qk_video.getCurrentPosition();
                        long currentPoint=iMediaPlayer.getCurrentPosition();
                        tv_alltime.setText(generateTime(currentPoint));
                        int pro= (current*1000)/totle;
                        video_progressbar.setProgress(pro);
                        video_progressbar.setSecondaryProgress(buff);
                    }
                });

            }

        });

        video_progressbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 //  pro   tot
                //   c      d
                if(fromUser){
                    int buff=qk_video.getBufferPercentage();
                    int cur=progress*qk_video.getDuration()/seekBar.getMax();
                    if(buff<cur){
                        Toast.makeText(VideoPlay.this, "正在加载，不要着急", Toast.LENGTH_SHORT).show();
                    }
                    qk_video.seekTo(cur);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 624);
            video_lay.setLayoutParams(lp);
            getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
        }
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            video_lay.setLayoutParams(lp);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        }
    }


    private void PlayVideo(String videoUrl) {
        QkMediaPlayer.loadLibrariesOnce(null);
        QkMediaPlayer.native_profileBegin("libqkplayer.so");
        qk_video.setVideoPath(videoUrl);
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
            case R.id.btn_back:
                this.finish();
                break;
            case R.id.btn_share:
                Toast.makeText(VideoPlay.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_big:
                if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
            case  R.id.iv_paly:
                if(qk_video.isPlaying()){
                    qk_video.pause();
                }else {
                    qk_video.start();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {
            //按下
            case MotionEvent.ACTION_DOWN:

                if (!isVISIBLE) {
                    //显示出来
                    changeState(true);
                } else {
                    //
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

    private void changeState(boolean b) {
        isVISIBLE = b;
        if(b){
            rl_control.setVisibility(View.GONE);
        }else {
            rl_control.setVisibility(View.VISIBLE);
        }

    }


    private static String generateTime(long position)
    {
        int totalSeconds = (int) ((position / 1000.0) + 0.5);

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        if (hours > 0)
        {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds).toString();
        }
        else
        {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds).toString();
        }
    }

}
