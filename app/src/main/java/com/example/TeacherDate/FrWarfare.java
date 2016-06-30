package com.example.TeacherDate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Activity.Wardetails;
import com.example.adapter.WarfareAdapter;
import com.example.androidnetwork.R;
import com.example.model.Warfare;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/20.
 * <p/>
 * 战法
 */
public class FrWarfare extends Fragment {
    private View view;
    private XRecyclerView lv_warfare;
    private String warfareUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/gettactics";
    private WarfareAdapter adapter;
    private Map<String, Object> map = new ArrayMap<>();
    private List<Warfare> list = new ArrayList<>();
    private int teacherId;
    private int page = 1;
    private TextView tv_war;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.frwarfare, container, false);

        init();
        return view;
    }

    private void init() {
        teacherId = (int) getArguments().getSerializable("teacherId");
        GetWarFare();
        lv_warfare = (XRecyclerView) view.findViewById(R.id.lv_warfare);
        tv_war = (TextView) view.findViewById(R.id.tv_war);
        adapter = new WarfareAdapter(getActivity(), list);
        lv_warfare.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_warfare.setLayoutManager(manager);
        lv_warfare.setPullRefreshEnabled(false);
        adapter.setOnItemClickLinsstenr(new WarfareAdapter.WarfareAdapterOnItemClickLinsstenr() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Wardetails.class);
                startActivity(intent);
            }
        });
        lv_warfare.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });


    }

    private void GetWarFare() {
        map.clear();
        map.put("teacherId", teacherId);
        map.put("page", page);
        list.clear();
        RequestParams params = NetUtils.sendHttpGet(warfareUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                Toast.makeText(getActivity(), "" + result.length(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "" + result, Toast.LENGTH_SHORT).show();


                if (result == null && result.length() <= 0) {
                    lv_warfare.setVisibility(View.VISIBLE);
                }

                if (result != null && result.length() > 0) {
                    // [{"id":1,"title":"标题","desc":"描述","image":"封面图片","orderNum":订阅人数}]
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            String title = object.getString("title");
                            String desc = object.getString("desc");
                            String image = object.getString("image");
                            int orderNum = object.getInt("orderNum");
                            Warfare warfare = new Warfare();
                            warfare.setId(id);
                            warfare.setTitle(title);
                            warfare.setDesc(desc);
                            warfare.setImage(image);
                            warfare.setOrderNum(orderNum);
                            list.add(warfare);
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
