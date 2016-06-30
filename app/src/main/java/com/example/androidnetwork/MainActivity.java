package com.example.androidnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.application.Settings;
import com.example.utils.StoreUtils;
import com.example.Activity.Login;
import com.example.fragment.FragmentMain;
import com.example.fragment.FragmentThree;
import com.example.fragment.FragmentTwo;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private FragmentMain fragmentMain;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private RelativeLayout rl_index;
    private RelativeLayout rl_play;
    private RelativeLayout rl_my;
    private FragmentManager fm;
    private View currentButton;
    private ImageButton radioOne;
    private ImageButton radioTwo;
    private ImageButton radioThree;
    private FragmentTransaction ft;

    private LinearLayout maingroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        maingroup = (LinearLayout) findViewById(R.id.maingroup);


        rl_index = (RelativeLayout) findViewById(R.id.rl_index);
        radioOne = (ImageButton) findViewById(R.id.radioOne);
        rl_play = (RelativeLayout) findViewById(R.id.rl_play);
        radioTwo = (ImageButton) findViewById(R.id.radioTwo);
        rl_my = (RelativeLayout) findViewById(R.id.rl_my);
        radioThree = (ImageButton) findViewById(R.id.radioThree);
        rl_play.setOnClickListener(this);
        rl_index.setOnClickListener(this);
        rl_my.setOnClickListener(this);
        radioOne.setOnClickListener(indexClick);
        radioTwo.setOnClickListener(playClick);
        radioThree.setOnClickListener(myClick);
        radioOne.performClick();
    }

    private View.OnClickListener indexClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentMain = new FragmentMain();
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.viewpagr, fragmentMain);
            ft.commit();
            setButton(v);

        }
    };


    private View.OnClickListener playClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentTwo = new FragmentTwo();
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.viewpagr, fragmentTwo);
            ft.commit();
            setButton(v);
        }
    };


    private View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int uId = StoreUtils.getUserInfo(MainActivity.this);
            if (uId > 0) {
                fragmentThree = new FragmentThree();
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.viewpagr, fragmentThree);
                ft.commit();
                setButton(v);
            } else {
                Intent login = new Intent();
                login.setClass(MainActivity.this, Login.class);
                MainActivity.this.startActivity(login);
            }


        }
    };


    private void setButton(View v) {
        // 将上个控件设置为可点击
        if (currentButton != null && currentButton.getId() != v.getId()) {
            currentButton.setEnabled(true);
        }
        // 当前按钮设置为不可点击，
        v.setEnabled(false);
        currentButton = v;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 两秒之内改变退出状态
        } else {
            //退出程序
            finish();
            System.exit(0);
            Toast.makeText(MainActivity.this, "执行退出APP", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_index:
                radioOne.performClick();
                break;
            case R.id.rl_play:
                radioTwo.performClick();
                break;
            case R.id.rl_my:
                radioThree.performClick();
                break;
            default:
                break;
        }

    }
}
