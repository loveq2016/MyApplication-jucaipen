package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Activity.VideoPlay;
import com.example.Live.LiveRoom;
import com.example.adapter.OldAdapter;
import com.example.adapter.PersonSunAdaper;
import com.example.adapter.TodayAdapter;
import com.example.androidnetwork.R;
import com.example.model.Special;
import com.example.model.Video;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.view.MyGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/16.
 * <p/>
 * 演播室 简介
 */
public class IntroFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View view;
    private TodayAdapter todayAdapter;//选集
    private PersonSunAdaper personSunAdaper;//人气榜
    private OldAdapter oldAdapter;//相关视频
    private String abouVideotUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getrelatevideo";
    private RecyclerView recy_video;
    private RelativeLayout xuanji_more;
    private RelativeLayout about_video;
    private RelativeLayout tuijian_more;
    private TextView video_name;
    private MyGridView video_mygrid;
    private TextView play_num;
    private MyGridView look_video;
    private Map<String, Object> map = new HashMap<>();
    private List<Special> specialList = new ArrayList<>();
    private int classId;
    private List<Special> videoList = new ArrayList<>();
    private String title;
    private int hit;
    private boolean isSpecial;
    private Intent intent=new Intent();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.introfragment, container, false);
        init();
        return view;
    }


    private void GetAboutVideo(int classId) {
        map.clear();
        map.put("isIndex", 0);
        map.put("typeId", 2);
        map.put("classId", classId);
        RequestParams params = NetUtils.sendHttpGet(abouVideotUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    videoList = JsonUtil.getSpecal(result);
                    oldAdapter.setList(videoList);
                }
                oldAdapter.notifyDataSetChanged();

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

    private void GetSelectVideo() {
        map.put("isIndex", 0);
        map.put("typeId", 1);
        map.put("classId", classId);
        RequestParams params = NetUtils.sendHttpGet(abouVideotUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    specialList = JsonUtil.getSpecal(result);
                    todayAdapter.setList(specialList);
                }
                todayAdapter.notifyDataSetChanged();
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
        title = getArguments().getString("title", "");
        hit = getArguments().getInt("hit", 0);
        classId = getArguments().getInt("classId", -1);
        isSpecial = getArguments().getBoolean("isSpecial", false);

        recy_video = (RecyclerView) view.findViewById(R.id.recy_video);
        video_mygrid = (MyGridView) view.findViewById(R.id.video_mygrid);
        video_mygrid.setOnItemClickListener(this);
        look_video = (MyGridView) view.findViewById(R.id.look_video);
        look_video.setOnItemClickListener(this);
        video_name = (TextView) view.findViewById(R.id.video_name);
        video_name.setText(title);
        play_num = (TextView) view.findViewById(R.id.play_num);
        play_num.setText(hit + " 次播放");

        tuijian_more = (RelativeLayout) view.findViewById(R.id.tuijian_more);
        tuijian_more.setOnClickListener(this);


        todayAdapter = new TodayAdapter(getActivity(), specialList);

        oldAdapter = new OldAdapter(getActivity(), videoList);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy_video.setLayoutManager(lm);

        recy_video.setAdapter(todayAdapter);


        video_mygrid.setAdapter(oldAdapter);
        look_video.setAdapter(oldAdapter);


        xuanji_more = (RelativeLayout) view.findViewById(R.id.xuanji_more);
        xuanji_more.setOnClickListener(this);

        about_video = (RelativeLayout) view.findViewById(R.id.about_video);
        about_video.setOnClickListener(this);

        todayAdapter.setOnItemOnClick(new TodayAdapter.TodayAdapterOnItemOnClick() {
            @Override
            public void onClick(View v, int position) {

                intent.putExtra("id", specialList.get(position).getId());
                intent.putExtra("videoUrl", specialList.get(position).getVideoUrl());
                intent.putExtra("classId", specialList.get(position).getClassId());
                intent.putExtra("title",specialList.get(position).getTitle());
                intent.putExtra("hit",specialList.get(position).getPlayCount());
                intent.putExtra("isSpecial",specialList.get(position).isIsSpecial());
                intent.putExtra("isCharge",specialList.get(position).isIsCharge());

                intent.setClass(getActivity(), VideoPlay.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        if (isSpecial) {
            //获取选集视频信息
            GetSelectVideo();
            about_video.setVisibility(View.GONE);
            video_mygrid.setVisibility(View.GONE);
        } else {
            //获取相关视频参数
            if (classId > 0) {
                GetAboutVideo(classId);
            }
            xuanji_more.setVisibility(View.GONE);
            recy_video.setVisibility(View.GONE);
        }

        GetAboutVideo(classId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xuanji_more:
                Toast.makeText(getActivity(), "选集更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_video:
                Toast.makeText(getActivity(), "相关视频更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tuijian_more:
                Toast.makeText(getActivity(), "推荐视频更多", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent.putExtra("id", videoList.get(position).getId());
        intent.putExtra("videoUrl", videoList.get(position).getVideoUrl());
        intent.putExtra("classId", videoList.get(position).getClassId());
        intent.putExtra("title",videoList.get(position).getTitle());
        intent.putExtra("hit",videoList.get(position).getPlayCount());
        intent.putExtra("isSpecial",videoList.get(position).isIsSpecial());
        intent.putExtra("isCharge",videoList.get(position).isIsCharge());
        intent.setClass(getActivity(), VideoPlay.class);
        startActivity(intent);
        getActivity().finish();
    }
}
