package com.example.Live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Activity.HotCareful;
import com.example.adapter.TeacherAdapter;
import com.example.androidnetwork.R;
import com.example.model.TeacherDate;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

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
public class TeacherIdea extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private ListView teacherListview;
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
        GetIdea();
        teacherListview = (ListView) view.findViewById(R.id.teacherListview);
        teacherListview.setOnItemClickListener(this);
        adapter = new TeacherAdapter(getActivity(), list);
        teacherListview.setAdapter(adapter);

    }

    private void GetIdea() {
        map.clear();
        map.put("isIndex", 0);
        map.put("teacherId", teacherId);
        map.put("typeId", 0);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(ideaUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    //id":795,"title":"证监会：要加强对投融资者的教育","desc":","insertDate":"2016-05-18 09:46:07.837","readNum":7,"isFree":0}]
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("id",list.get(position).getId());
        intent.setClass(getActivity(), HotCareful.class);
        //intent.setClass(getActivity(), IdeaSee.class);
        startActivity(intent);
    }
}
