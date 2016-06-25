package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.OldAdapter;
import com.example.adapter.PersonSunAdaper;
import com.example.adapter.TodayAdapter;
import com.example.androidnetwork.R;
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
public class IntroFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TodayAdapter todayAdapter;//选集
    private PersonSunAdaper personSunAdaper;//人气榜
    private OldAdapter oldAdapter;//相关视频
    private String abouVideotUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getrelatevideo";
    private RecyclerView recy_video;
    private List<String> todays = new ArrayList<>();
    private RelativeLayout xuanji_more;
    private RelativeLayout about_video;
    private RelativeLayout tuijian_more;
    private TextView video_name;
    private MyGridView video_mygrid;
    private TextView play_num;
    private MyGridView look_video;
    private Map<String, Object> map = new HashMap<>();
    private int classId;
    private List<Video> videoList = new ArrayList<>();
    private String title;
    private int hit;

//    public IntroFragment(int classId) {
//
//        this.classId = classId;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.introfragment, container, false);

        init();

        //获取选集视频信息
        GetSelectVideo();

        //获取相关视频参数
        if (classId > 0) {
            GetAboutVideo(classId);
        }

        return view;
    }


    private void GetAboutVideo(int classId) {
        map.clear();
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
                    videoList = JsonUtil.getaboutvideo(result);
                    oldAdapter.setList(videoList);
                    oldAdapter.notifyDataSetChanged();

                }
                // [{"page":0,"totlePage":0,"id":24,"title":"证监会发布融资融券新规"
                // "imageUrl":"http:7_300_200.png",playCount  videoDate
                // "desc":"证监会发布融资融券新规","videoUrl":"http://tv.so550_0_9001_0","classId":1},


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
        map.put("", 6);
        RequestParams params = NetUtils.sendHttpGet("", map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {

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
//        bundle.putString("title",title);
//        bundle.putInt("hit",hit);
//        bundle.putInt("classId",classId);

        title = getArguments().getString("title", "");
        hit = getArguments().getInt("hit", 0);
        classId = getArguments().getInt("classId", -1);
        recy_video = (RecyclerView) view.findViewById(R.id.recy_video);
        video_mygrid = (MyGridView) view.findViewById(R.id.video_mygrid);
        look_video = (MyGridView) view.findViewById(R.id.look_video);
        video_name = (TextView) view.findViewById(R.id.video_name);
        video_name.setText(title);
        play_num = (TextView) view.findViewById(R.id.play_num);
        play_num.setText(hit + " 次播放");

        xuanji_more = (RelativeLayout) view.findViewById(R.id.xuanji_more);
        xuanji_more.setOnClickListener(this);
        about_video = (RelativeLayout) view.findViewById(R.id.about_video);
        about_video.setOnClickListener(this);
        tuijian_more = (RelativeLayout) view.findViewById(R.id.tuijian_more);
        tuijian_more.setOnClickListener(this);


        todayAdapter = new TodayAdapter(todays);
        oldAdapter = new OldAdapter(getActivity(), videoList);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);

        recy_video.setLayoutManager(lm);

        recy_video.setAdapter(todayAdapter);

        video_mygrid.setAdapter(oldAdapter);
        look_video.setAdapter(oldAdapter);

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
}
