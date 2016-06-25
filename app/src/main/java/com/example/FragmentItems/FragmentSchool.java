package com.example.FragmentItems;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.Activity.SchoolSecurity;
import com.example.Activity.VideoPlay;
import com.example.adapter.MyAdapter;
import com.example.adapter.VideioAdapter;
import com.example.model.AdviseBean;
import com.example.model.ButtonType;
import com.example.model.School;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.view.MyGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jucaipen on 16/5/10.
 * <p/>
 * 学院
 */
public class FragmentSchool extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private View view;
    private ViewPager schoolpager;
    //存放引到图片数组
    private List<View> list = new ArrayList<View>();
    private MyAdapter adapter;
    private ImageLoader loder = ImageLoader.getInstance();
    private MyGridView school_grd;
    private VideioAdapter videioAdapter;
    private int ids[] = {R.id.x1, R.id.x2, R.id.x3};
    private String schoolUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getteachlist";
    private String butonUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getbasetype";
    private List<School> schoolList = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private List<ButtonType> typeList = new ArrayList<>();
    private RadioGroup school_radgrp;
    public String ADVISE_PATH = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getadvise";
    RadioButton button;
    private int images[] = {R.drawable.zhengquan, R.drawable.qihuo, R.drawable.lianghua, R.drawable.jijin};
    private ScrollView school_scroll;
    //执行图片轮播
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                int item = schoolpager.getCurrentItem();
                if (item < adapter.getCount() - 1) {
                    schoolpager.setCurrentItem(item + 1);
                } else {
                    schoolpager.setCurrentItem(0, false);
                }
                handler.sendEmptyMessageDelayed(100, 2000);
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_school, container, false);

        init();
        GetButton();

        GetSchool();

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (schoolpager != null) {
                schoolpager.setCurrentItem(0);
                handler.sendEmptyMessageDelayed(100, 1000);
            }
        } else {
            handler.removeMessages(100);
        }
    }

    private void GetButton() {
        school_radgrp = (RadioGroup) view.findViewById(R.id.school_radgrp);
        map.put("bigId", 0);
        map.put("leavel", 0);
        RequestParams params = NetUtils.sendHttpGet(butonUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onSuccess(String result) {

                if (result != null) {
                    typeList = JsonUtil.getbutton(result);
                    for (int i = 0; i < typeList.size(); i++) {
                        button = new RadioButton(getActivity());
                        RadioGroup.LayoutParams lm = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                        button.setLayoutParams(lm);
                        button.setButtonDrawable(0);
                        button.setId(typeList.get(i).getId());
                        button.setCompoundDrawablesRelativeWithIntrinsicBounds(0, images[i], 0, 0);
                        button.setCompoundDrawablePadding(7);
                        button.setGravity(Gravity.CENTER_HORIZONTAL);
                        button.setTextSize(11);
                        button.setTextColor(Color.parseColor("#aaaaaa"));
                        button.setText(typeList.get(i).getBigType());
                        school_radgrp.addView(button);
                        button.setOnClickListener(FragmentSchool.this);
                    }

                }
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

    private void GetSchool() {
        RequestParams params = NetUtils.sendHttpGet(schoolUrl, null);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    schoolList = JsonUtil.getschool(result);
                    videioAdapter.setList(schoolList);
                    videioAdapter.notifyDataSetChanged();
                }
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

    private void init() {
        adapter = new MyAdapter(list);
        school_grd = (MyGridView) view.findViewById(R.id.school_grd);
        school_grd.setOnItemClickListener(this);
        videioAdapter = new VideioAdapter(getActivity(), schoolList);
        schoolpager = (ViewPager) view.findViewById(R.id.schoolpager);
        schoolpager.setAdapter(adapter);
        school_grd.setAdapter(videioAdapter);
        //获取广告
        GetAdvace();
        school_scroll = (ScrollView) view.findViewById(R.id.school_scroll);
        school_scroll.smoothScrollTo(0, 0);

    }

    private void GetAdvace() {
        //获取首页广告
        map.clear();
        map.put("type", 1);
        RequestParams params = NetUtils.sendHttpGet(ADVISE_PATH, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    Gson gson = new Gson();
                    List<AdviseBean> advises = gson.fromJson(result, new TypeToken<List<AdviseBean>>() {
                    }.getType());
                    for (AdviseBean a : advises) {
                        String url = a.getImageUrl();
                        ImageView iv = new ImageView(getActivity());
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        Glide.with(getActivity()).load(url)
                                .placeholder(R.drawable.approve)
                                .into(iv);
                        list.add(iv);
                    }
                    adapter.notifyDataSetChanged();
                    schoolpager.setCurrentItem(0);
                    handler.sendEmptyMessageDelayed(100, 1000);
                    schoolpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            for (int i = 0; i < ids.length; i++) {
                                ImageView iv = (ImageView) view.findViewById(ids[i]);
                                if (i == position) {
                                    iv.setImageResource(R.drawable.yiru_xiaoyuan);
                                } else {
                                    iv.setImageResource(R.drawable.xiaoyuan);
                                }
                            }

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });

                }
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();

        switch (parent.getId()) {
            case R.id.school_grd:
                intent.putExtra("title",schoolList.get(position).getTitle());
                intent.putExtra("hit",schoolList.get(position).getHits());
                intent.putExtra("videoUrl",schoolList.get(position).getVideoUrl());
                intent.putExtra("id", schoolList.get(position).getId());
                intent.setClass(getActivity(), VideoPlay.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int position = v.getId();
        Intent intent = new Intent();
        intent.putExtra("buttonId", position);
        intent.setClass(getActivity(), SchoolSecurity.class);
        startActivity(intent);

    }
}
