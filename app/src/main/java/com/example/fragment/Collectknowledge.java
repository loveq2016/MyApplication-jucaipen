package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Activity.HotCareful;
import com.example.adapter.KnowledgeAdapter;
import com.example.androidnetwork.R;
import com.example.model.Collect;
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
 * <p/>
 * 收藏知识
 */
public class Collectknowledge extends Fragment {
    private View view;
    private KnowledgeAdapter adapter;
    private XRecyclerView lv_knowledge;
    private Map<String, Object> map = new HashMap<>();
    private String collectUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getcollect";
    private List<Collect> list = new ArrayList<>();
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.knowledge, container, false);
        init();
        GetCollectDate();
        return view;
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_knowledge = (XRecyclerView) view.findViewById(R.id.lv_knowledge);
        lv_knowledge.setLayoutManager(manager);
        lv_knowledge.setPullRefreshEnabled(false);
        adapter = new KnowledgeAdapter(getActivity(), list);
        lv_knowledge.setAdapter(adapter);

        adapter.setOnclickitemLinnser(new KnowledgeAdapter.OnclickitemLinnser() {
            @Override
            public void onClick(View v, int position) {
                Intent intent=new Intent();
                intent.putExtra("id",list.get(position).getId());
                intent.setClass(getActivity(), HotCareful.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "position=" + position, Toast.LENGTH_SHORT).show();
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
                        // adapter.setList(list);
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
