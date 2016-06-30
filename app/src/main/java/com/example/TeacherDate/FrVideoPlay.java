package com.example.TeacherDate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Activity.VideoPlay;
import com.example.Live.LiveRoom;
import com.example.adapter.VideioAdapter;
import com.example.androidnetwork.R;
import com.example.fragment.FragmentTwo;
import com.example.model.School;
import com.example.utils.NetUtils;
import com.example.view.MyGridView;
import com.example.view.TestListView;

import org.json.JSONArray;
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
 * Created by Administrator on 2016/5/14.
 * <p/>
 * 视频直播
 */
public class FrVideoPlay extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View view;
    private FragmentActivity context;
    private MyGridView writer_history;
    private String videoUrl = "http://192.168.1.134:8080/Jucaipen/jucaipen/getideaask";
    private VideioAdapter adapter;
    private List<School> list = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private int teacherId;
    private int page = 1;
    private TextView video_play;
    private TextView video_personnum;
    private RelativeLayout video_rel;
    private ImageView video_pic;
    private ScrollView play_scroll;
    private TextView tv_none;
    private ProgressBar play_progress;
    private TextView live_body;
    private int liveId;
    private int isEnd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fr_videoplay, container, false);

        init();
        return view;
    }

    private void init() {
        teacherId = (int) getArguments().getSerializable("teacherId");
        video_play = (TextView) view.findViewById(R.id.video_play);
        live_body = (TextView) view.findViewById(R.id.live_body);
        video_personnum = (TextView) view.findViewById(R.id.video_personnum);
        video_rel = (RelativeLayout) view.findViewById(R.id.video_rel);
        video_pic = (ImageView) view.findViewById(R.id.video_pic);
        play_scroll = (ScrollView) view.findViewById(R.id.play_scroll);
        tv_none = (TextView) view.findViewById(R.id.tv_none);
        play_progress = (ProgressBar) view.findViewById(R.id.play_progress);
        video_rel.setOnClickListener(this);
        context = getActivity();
        GetWriterlive();
        writer_history = (MyGridView) view.findViewById(R.id.writer_history);
        writer_history.setOnItemClickListener(this);
        adapter = new VideioAdapter(getActivity(), list);
        writer_history.setAdapter(adapter);
    }

    private void GetWriterlive() {
        map.clear();
        map.put("teacherId", teacherId);
        map.put("typeId", 3);
        map.put("isIndex", 1);
        map.put("page", page);

        RequestParams params = NetUtils.sendHttpGet(videoUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                play_scroll.setVisibility(View.VISIBLE);
                play_progress.setVisibility(View.GONE);
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        JSONObject object = array.getJSONObject(0);
                        String err_msg = object.optString("err_msg", "");
                        if (!err_msg.equals("")) {
                            play_scroll.setVisibility(View.GONE);
                            tv_none.setVisibility(View.VISIBLE);
                            return;
                        }

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = array.getJSONObject(0);
                            liveId = object1.getInt("id");
                            String image = object.optString("image", "");
                            String startDate = object.optString("startDate", "");
                            String title = object.optString("title", "");
                            isEnd = object.optInt("isEnd", -1);
                            if (isEnd == 1) {
                                video_play.setText("未开始");
                            } else if (isEnd == 2) {
                                video_play.setText("正在直播");
                            } else if (isEnd == 3) {
                                video_play.setText("已结束");
                            }
                            live_body.setText(title);
                            video_personnum.setText("20人");
                            Glide.with(getActivity()).load(image)
                                    .placeholder(R.drawable.approve)
                                    .into(video_pic);

                            JSONArray array1 = array.getJSONArray(1);
                            for (int a = 0; a < array1.length(); a++) {
                                JSONObject object2 = array1.getJSONObject(a);
                                int ids = object2.optInt("id", -1);
                                int hits = object2.optInt("hits", -1);
                                String images = object2.optString("image", "");
                                String titles = object2.optString("title", "");
                                String startDates = object2.optString("startDate", "");
                                String desc = object2.optString("desc", "");
                                String videoUrl = object2.optString("videoUrl", "");
                                int classId = object2.optInt("classId", -1);
                                boolean isSpecial = object2.optBoolean("isSpecial", false);
                                boolean isCharge = object2.optBoolean("isCharge", false);
                                School school = new School();
                                school.setId(ids);
                                school.setHits(hits);
                                school.setImage(images);
                                school.setTitle(titles);
                                school.setStartDate(startDates);
                                school.setDesc(desc);
                                school.setVideoUrl(videoUrl);
                                school.setClassId(classId);
                                school.setSpecial(isSpecial);
                                school.setCharge(isCharge);
                                list.add(school);
                            }

                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_rel:
                if (isEnd == 2) {
                    Intent intent = new Intent();
                    intent.putExtra("liveId", liveId);
                    intent.setClass(getActivity(), LiveRoom.class);
                    startActivity(intent);
                }

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("id", list.get(position).getId());
        intent.putExtra("videoUrl", list.get(position).getVideoUrl());
        intent.putExtra("classId", list.get(position).getClassId());
        intent.putExtra("title", list.get(position).getTitle());
        intent.putExtra("hit", list.get(position).getHits());
        intent.putExtra("isSpecial", list.get(position).isSpecial());
        intent.putExtra("isCharge", list.get(position).isCharge());
        intent.setClass(getActivity(), VideoPlay.class);
        startActivity(intent);
    }
}
