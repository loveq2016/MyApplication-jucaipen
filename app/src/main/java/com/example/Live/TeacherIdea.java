package com.example.Live;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Activity.HotCareful;
import com.example.adapter.TeacherAdapter;
import com.example.androidnetwork.R;
import com.example.model.TeacherDate;
import com.example.utils.NetUtils;
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
 * Created by Administrator on 2016/5/12.
 * 老师观点
 */
public class TeacherIdea extends Fragment {
    private View view;
    private XRecyclerView teacherListview;
    private TeacherAdapter adapter;
    private String ideaUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getideaask";
    private Map<String, Object> map = new HashMap<>();
    private int page = 1;
    private int teacherId;
    private List<TeacherDate> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.teacheridea, container, false);

        init();
        return view;
    }

    private void init() {
        teacherId = (int) getArguments().getSerializable("teacherId");
        GetIdea(page);
        teacherListview = (XRecyclerView) view.findViewById(R.id.teacherListview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        teacherListview.setLayoutManager(manager);

        teacherListview.setPullRefreshEnabled(false);
        adapter = new TeacherAdapter(getActivity(), list);
        teacherListview.setAdapter(adapter);
        adapter.setxRecyclerViewOnItemLlick(new TeacherAdapter.XRecyclerViewOnItemLlick() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent();
                intent.putExtra("id", list.get(position).getId());
                intent.setClass(getActivity(), HotCareful.class);
                startActivity(intent);
            }
        });
        teacherListview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {

                if (list != null && list.size() > 0) {
                    //int totlePage= list.get(0).getTotpager();
                    if (5 >= page) {
                        int p = page++;
                        GetIdea(p);
                    }

                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        teacherListview.loadMoreComplete();
                    }
                }, 3000);

            }
        });

    }

    private void GetIdea(int pages) {
        map.clear();
        map.put("isIndex", 0);
        map.put("teacherId", teacherId);
        map.put("typeId", 0);
        map.put("page", pages);
        RequestParams params = NetUtils.sendHttpGet(ideaUrl, map);
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
                            int id = object.getInt("id");
                            String title = object.getString("title");
                            String desc = object.getString("desc");
                            String insertDate = object.getString("insertDate");
                            int readNum = object.getInt("readNum");
                            int isFree = object.getInt("isFree");
                            TeacherDate date = new TeacherDate();
                            date.setId(id);
                            date.setTitle(title);
                            date.setDesc(desc);
                            date.setInsertDate(insertDate);
                            date.setReadNum(readNum);
                            date.setIsFree(isFree);
                            list.add(date);
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

}
