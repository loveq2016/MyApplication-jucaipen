package com.example.Live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.example.utils.StringUntils;
import com.example.adapter.TextVideoAdapter;
import com.example.model.TextVideo;
import com.example.utils.NetUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8.
 * <p/>
 * 视频直播
 */
public class LiveVideo extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private GridView live_gv;
    private TextVideoAdapter adapter;
    private String liveUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getlivelist";
    private Map<String, Object> map = new HashMap<>();
    private int page = 1;
    private List<TextVideo> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.livevideo, container, false);

        init();
        GetLiveVideo();

        return view;
    }

    private void GetLiveVideo() {
        map.put("liveType", 1);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(liveUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                if (result != null) {
                    list = JsonUtil.getTextVideo(result);
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
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
        live_gv = (GridView) view.findViewById(R.id.live_gv);
        live_gv.setOnItemClickListener(this);
        adapter = new TextVideoAdapter(getActivity(), list);
        live_gv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("liveId",list.get(position).getId());
        intent.putExtra("teacherId", list.get(position).getTeacherId());
        intent.setClass(getActivity(), LiveRoom.class);
        startActivity(intent);

    }
}
