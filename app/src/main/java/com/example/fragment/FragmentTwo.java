package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.androidnetwork.R;
import com.example.utils.NetUtils;
import com.example.view.media.QkVideoView;
import com.qukan.playsdk.QkMediaPlayer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jucaipen on 16/5/5.
 * 演播室
 */
public class FragmentTwo extends Fragment implements View.OnClickListener {
    private View view;
    private Radiointro radiointro;//简介
    private RadioComm radioComm;//评论
    private RadioButton intro_radio;
    private RadioButton comm_radio;
    private RadioGroup radio_group;
    private QkVideoView qk_yb;
    private LinearLayout framelayout;
    private List<Fragment> list = new ArrayList<>();
    private LinearLayout lay;
    private String postUrl = "";
    private Map<String, Object> map = new HashMap<>();
    private int liveId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.radiotwo, container, false);
        init();
        select(0);
        return view;
    }


    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lay = (LinearLayout) getActivity().findViewById(R.id.maingroup);
    }

    @Override
    public void onResume() {
        super.onResume();
        qk_yb.resume();
    }



    @Override
    public void onPause() {
        super.onPause();
        qk_yb.pause();
    }

    private void select(int i) {
        FragmentManager fm = getFragmentManager();
        //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //先隐藏 Fragment
        switch (i) {
            case 0:
                if (radiointro == null) {
                    radiointro = new Radiointro();
                    ft.add(R.id.framelayout, radiointro).commit();
                } else {
                    ft.show(radiointro).commit();
                }
                break;
            case 1:
                if (radioComm == null) {
                    radioComm = new RadioComm();
                    ft.add(R.id.framelayout, radioComm).commit();
                } else {
                    ft.show(radioComm).commit();
                }
                break;
            default:
                break;
        }

    }

    private void hidtFragment(FragmentTransaction ft) {
        if (radiointro != null) {
            ft.hide(radiointro);
        }
        if (radioComm != null) {
            ft.hide(radioComm);
        }


    }

    private void init() {
        //用户上线
        PoastTop();
        qk_yb= (QkVideoView) view.findViewById(R.id.qk_yb);
        framelayout = (LinearLayout) view.findViewById(R.id.framelayout);
        intro_radio = (RadioButton) view.findViewById(R.id.intro_radio);
        intro_radio.setOnClickListener(this);
        comm_radio = (RadioButton) view.findViewById(R.id.comm_radio);
        comm_radio.setOnClickListener(this);
    }

    private void PoastTop() {
        //   opType    1   上线       2    聊天      3   下线
        // {"fromId":1,"liveId:1","opType":2,"toId":0,msg:"hello"}


        map.clear();
        map.put("opType", 1);
        RequestParams params = NetUtils.sendHttpGet(postUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.intro_radio:
                lay.setVisibility(View.VISIBLE);
                select(0);
                break;
            case R.id.comm_radio:
                lay.setVisibility(View.GONE);
                select(1);
                break;
        }

    }
}

