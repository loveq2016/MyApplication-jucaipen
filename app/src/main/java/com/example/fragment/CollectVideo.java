package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Activity.VideoPlay;
import com.example.adapter.LiveAdapter;
import com.example.androidnetwork.R;
import com.example.model.Collect;
import com.example.model.Video;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StoreUtils;
import com.example.utils.StringUntils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
 * Created by Administrator on 2016/6/2.
 * 收藏视频
 */
public class CollectVideo extends Fragment {
    private View view;
    private XRecyclerView lv_video;
    private Map<String, Object> map = new HashMap<>();
    private String collectUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getcollect";
    private LiveAdapter adapter;
    private int page = 1;
    private List<Collect> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.collectvideo, container, false);
        init();
        GetCollectDate();
        return view;
    }
 /*URL：http://192.168.1.134:8080/Jucaipen/jucaipen/getcollect
    请求方式：get
    请求参数： userId
            page
    type    0    收藏的视频     1  收藏的知识
    返回参数：
            [{"id":5,"insertDate":"收藏时间","title":"标题"，","images":“封面图片”}]*/

    private void init() {

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_video = (XRecyclerView) view.findViewById(R.id.lv_video);
        lv_video.setPullRefreshEnabled(false);
        lv_video.setLayoutManager(manager);
        adapter = new LiveAdapter(getActivity(), list);
        lv_video.setAdapter(adapter);
        adapter.setRecyclerViewItemClickListener(new LiveAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View v, int postion) {
                Toast.makeText(getActivity(), "postion" + postion, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(getActivity(), VideoPlay.class);
                startActivity(intent);
            }
        });

    }


    private void GetCollectDate() {
        map.clear();
        map.put("userId", StoreUtils.getUserInfo(getActivity()));
        map.put("type", 1);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(collectUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.optInt("id", -1);
                            String insertDate = object.optString("insertDate", "");
                            String title = object.optString("title", "");
                            String images = object.optString("images", "");
                            Collect collect = new Collect();
                            collect.setId(id);
                            collect.setInsertDate(insertDate);
                            collect.setTitle(title);
                            collect.setImages(images);
                            list.add(collect);
                        }
                        adapter.setList(list);
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
}
