package com.example.TeacherDate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.WriterAdapter;
import com.example.androidnetwork.R;
import com.example.model.School;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/14.
 * <p/>
 * 文字直播
 */
public class FrWriterLive extends Fragment {
    private String writerUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getideaask";
    private View view;
    private ListView writer_history;
    private WriterAdapter adapter;
    private Map<String, Object> map = new ArrayMap<>();
    private int page = 1;
    private List<School> list = new ArrayList<>();
    private Context context;
    private int teacherId;
    private TextView tv_liveState;
    private TextView tv_liveTitle;
    private TextView tv_liveFans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fr_writerlive, container, false);
        teacherId = getArguments().getInt("teacherId");
        init();

        return view;
    }

    private void init() {
        context = getActivity();
        GetWriterlive();
        tv_liveFans = (TextView) view.findViewById(R.id.tv_liveFans);
        tv_liveTitle = (TextView) view.findViewById(R.id.tv_liveTitle);
        tv_liveState = (TextView) view.findViewById(R.id.tv_liveState);
        writer_history = (ListView) view.findViewById(R.id.writer_history);
        adapter = new WriterAdapter(context, list);
        writer_history.setAdapter(adapter);

    }

    private void GetWriterlive() {
        map.clear();
        map.put("teacherId", teacherId);
        map.put("page", page);
        map.put("typeId", 2);
        map.put("isIndex",1);
        RequestParams params = NetUtils.sendHttpGet(writerUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                list.clear();
                //[{"id":25,"title":"5.20","startDate":"2016-05-20 09:17:40.9"}
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            String title = object.getString("title");
                            String startDate = object.getString("startDate");

                            //是否收费（1否，2是）
                            int isFree = object.getInt("isFree");
                            //是否结束（1未开始，2进行中，3已结束）
                            int isEnd = object.getInt("isEnd");
                            String liveTitle = object.getString("liveTitle");
                            int hits=object.optInt("hits",0);
                            //引用学院实体类
                            School school = new School();
                            school.setId(id);
                            school.setTitle(title);
                            school.setHits(hits);
                            school.setStartDate(startDate);
                            school.setIsFree(isFree);
                            school.setIsEnd(isEnd);
                            school.setLiveTitle(liveTitle);
                            list.add(school);
                        }
                        adapter.notifyDataSetChanged();

                        if (list.size() > 0) {
                            tv_liveFans.setText("在线人数: "+list.get(0).getHits()+"");
                            int isEnd = list.get(0).getIsEnd();
                            String liveTitle = list.get(0).getLiveTitle();
                            ///是否结束（1未开始，2进行中，3已结束）
                            tv_liveTitle.setText(liveTitle);
                            if (isEnd == 1) {
                                tv_liveState.setText("未开始");
                            } else if (isEnd == 2) {
                                tv_liveState.setText("进行中");
                            } else {
                                tv_liveState.setText("已结束");
                            }
                        } else {
                            tv_liveState.setText("暂无直播");
                            tv_liveFans.setText("在线人数: 0");
                            tv_liveTitle.setVisibility(View.GONE);
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
        });
    }
}
