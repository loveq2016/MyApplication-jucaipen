package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.OldLiveAdapter;
import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.example.model.OldLive;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

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
 * 推荐视频 更多      //改为往期栏目更多
 */
public class VideoMore extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv_videomore;
    private int page = 1;
    private ImageButton video_back;
    private OldLiveAdapter oldLiveAdapter;
    private List<OldLive> list = new ArrayList<>();
    private String oldLiveUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getlastplay";
    private Map<String, Object> map = new HashMap<>();
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videomore);

        init();
    }

    private void init() {
        id = getIntent().getIntExtra("id", -1);
        lv_videomore = (ListView) findViewById(R.id.lv_videomore);
        lv_videomore.setOnItemClickListener(this);
        video_back = (ImageButton) findViewById(R.id.video_back);
        video_back.setOnClickListener(this);

        oldLiveAdapter = new OldLiveAdapter(this, list);
        lv_videomore.setAdapter(oldLiveAdapter);
        GetVideoDate();

    }

    private void GetVideoDate() {
        map.clear();
        map.put("isMore", 1);
        map.put("studioId", id);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(oldLiveUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {


                if (result != null && result.length() > 0) {
                    list = JsonUtil.getOldLive(result);
                    oldLiveAdapter.setList(list);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_back:
                this.finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("videoUrl",list.get(position).getVideoUrl());
        intent.setClass(this, VideoPlay.class);
        startActivity(intent);
    }
}
