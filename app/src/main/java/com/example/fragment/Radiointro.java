package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Activity.VideoMore;
import com.example.Activity.VideoPlay;
import com.example.adapter.MoodAdapter;
import com.example.adapter.OldLiveAdapter;
import com.example.adapter.PersonAdapter;
import com.example.adapter.TodayLive;
import com.example.androidnetwork.R;
import com.example.model.Studio;
import com.example.utils.JsonUtil;
import com.example.model.Famous;
import com.example.model.OldLive;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.view.media.QkVideoView;
import com.qukan.playsdk.QkMediaPlayer;

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
 * Created by Administrator on 2016/6/7.
 * <p/>
 * 直播室 简介
 */
public class Radiointro extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RecyclerView lv_today;
    private RecyclerView lv_moods;
    private ListView lv_old;
    //人气名家适配器
    private PersonAdapter personAdapter;
    private List<Famous> list = new ArrayList<>();
    private String todayLiveUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/gettodaystudio";
    private TodayLive todayLive;
    private MoodAdapter moodAdapter;
    private String personUrl="http://"+StringUntils.getHostName()+"/Jucaipen/jucaipen/getfanslist";
    private OldLiveAdapter oldLiveAdapter;
    //private String programUrl="http://"+ StringUntils.getHostName()+"/Jucaipen/jucaipen/getguest";
    private String videoUrl = "http://192.168.1.134:8080/Jucaipen/jucaipen/getcurrentstudio";
    private List<Studio> studios = new ArrayList<>();
    private TextView tv_vdurl;
    private View view;
    private RelativeLayout old_live;
    private TextView intro_live;
    private Map<String, Object> map = new HashMap<>();
    private QkVideoView qk_yb;
    private boolean mBackPressed;
    private List<OldLive> oldLives = new ArrayList<>();
    private String oldLiveUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getlastplay";
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.radiointro, container, false);

        init();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        qk_yb = (QkVideoView) getActivity().findViewById(R.id.qk_yb);
        tv_vdurl = (TextView) getActivity().findViewById(R.id.tv_vdurl);
        intro_live = (TextView) getActivity().findViewById(R.id.intro_live);
    }

    private void init() {

        /* 获取演播信息 */
        GetVideoDate();

        //获取今日直播
        GetTodyLive();

        //获取人气榜数据
        GetPerson();

        lv_today = (RecyclerView) view.findViewById(R.id.lv_today);
        lv_moods = (RecyclerView) view.findViewById(R.id.lv_moods);
        lv_old = (ListView) view.findViewById(R.id.lv_old);
        lv_old.setOnItemClickListener(this);
        old_live = (RelativeLayout) view.findViewById(R.id.old_live);
        old_live.setOnClickListener(this);


        todayLive = new TodayLive(studios);
        moodAdapter = new MoodAdapter(studios,getActivity());
        oldLiveAdapter = new OldLiveAdapter(getActivity(), oldLives);


        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        lv_today.setLayoutManager(lm);
        lv_today.setAdapter(todayLive);

        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity());
        lm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        lv_moods.setLayoutManager(lm1);
        lv_moods.setAdapter(moodAdapter);


        lv_old.setAdapter(oldLiveAdapter);


    }

    private void GetPerson() {
        RequestParams params=NetUtils.sendHttpGet(personUrl,null);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                studios.clear();
                if (result!=null){
                    //[{"id":6,"title":"股市早知道","renQi":61,"imageUrl":"
                    try {
                        JSONArray array=new JSONArray(result);
                        for (int i=0;i<array.length();i++){
                            JSONObject object=array.getJSONObject(i);
                            int id=object.getInt("id");
                            String title=object.getString("title");
                            int renQi=object.getInt("renQi");
                            String imageUrl=object.getString("imageUrl");

                            Studio studio=new Studio();
                            studio.setId(id);
                            studio.setTitle(title);
                            studio.setRenQi(renQi);
                            studio.setImageUrl(imageUrl);
                        }
                        moodAdapter.notifyDataSetChanged();

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

    private void GetOldLive() {
        map.put("isMore", 0);
        map.put("studioId", id);
        RequestParams params = NetUtils.sendHttpGet(oldLiveUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    //[{"id":427,"title":"投资互动《第二期5.19》","imageUrl":"http37.jpg","videoDate":0,"hits":17,"isSpecial":false}
                    oldLives = JsonUtil.getOldLive(result);
                    oldLiveAdapter.setList(oldLives);
                }
                oldLiveAdapter.notifyDataSetChanged();
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

    private void GetVideoDate() {
        //{"id":11,"renQi":48,"title":"量化投资","studioUrl":"http://hdl-w.quklive.com/live/w9458019977964845.flv

        map.clear();
        map.put("studioId", 2);

        RequestParams params = NetUtils.sendHttpGet(videoUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        String err_msg = object.optString("err_msg", "");
                        if (err_msg.length() > 0) {
                            //暂无直播
                            intro_live.setText("暂无直播");
                        } else {
                            id = object.getInt("id");

                             /*获取往期适配*/
                            GetOldLive();

                            String studioUrl = object.getString("studioUrl");
                            String title = object.getString("title");
                            tv_vdurl.setText(title);
                            PlayVideo(studioUrl);
                        }
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }


    private void PlayVideo(String videoUrl) {
        QkMediaPlayer.loadLibrariesOnce(null);
        QkMediaPlayer.native_profileBegin("libqkplayer.so");
        // qk_video.setMediaController(mMediaController);
        qk_yb.setVideoPath(videoUrl);
        qk_yb.start();

    }

//
//    @Override
//    public void onBackPressed() {
//        mBackPressed = true;
//        super.onBackPressed();
//    }

    @Override
    public void onStop() {
        super.onStop();

        if (mBackPressed || !qk_yb.isBackgroundPlayEnabled()) {
            qk_yb.stopPlayback();
            qk_yb.release(true);
            qk_yb.stopBackgroundPlay();
        } else {
            qk_yb.enterBackground();
        }
//        QkMediaPlayer.native_profileEnd();
    }


    //获取今日直播
    private void GetTodyLive() {
        RequestParams params = NetUtils.sendHttpGet(todayLiveUrl, null);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                studios.clear();
                if (result != null) {
                    //[{"id":6,"startDate":"2016-06-08 09:20:00.0","endDate":"2016-06-08 10:25:00.0",
                    // "renQi":50,"imageUrl":"http://img.jucaipen.com/jucaipenStudy/2016/5/27/2016527153042.jpg"}]
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            String title = object.getString("title");
                            String startDate = object.getString("startDate");
                            String endDate = object.getString("endDate");
                            int renQi = object.getInt("renQi");
                            String imageUrl = object.getString("imageUrl");
                            Studio studio = new Studio();
                            studio.setId(id);
                            studio.setStartDate(startDate);
                            studio.setEndDate(endDate);
                            studio.setRenQi(renQi);
                            studio.setTitle(title);
                            studio.setImageUrl(imageUrl);
                            studios.add(studio);
                        }
                        todayLive.notifyDataSetChanged();


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
            case R.id.old_live:
                Intent intent = new Intent();
                intent.putExtra("id", id);
                intent.setClass(getActivity(), VideoMore.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_old:
                Intent intent = new Intent();
                intent.putExtra("videoUrl", oldLives.get(position).getVideoUrl());
                Toast.makeText(getActivity(), "" + oldLives.get(position).getVideoUrl(), Toast.LENGTH_SHORT).show();
                intent.setClass(getActivity(), VideoPlay.class);
                startActivity(intent);
                break;
        }
    }
}
