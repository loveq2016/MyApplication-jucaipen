package com.example.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.adapter.NewsAdapter;
import com.example.androidnetwork.R;
import com.example.model.Rebate;
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
 * 最新榜单
 */
public class NewList extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView lv_news;
    private NewsAdapter adapter;
    private String Url = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/latestlist";
    private Map<String, Object> map = new HashMap<>();
    private RadioButton button_sun;
    private RadioButton button_month;
    private List<Rebate> list = new ArrayList<>();
    private int liveId;
    private int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.newlist, container, false);
        init();
        return view;
    }

    private void select(int i) {
        list.clear();
        adapter.notifyDataSetChanged();
        switch (i) {
            case 1:
                GetBangdan(0);
                break;
            case 2:
                GetBangdan(1);
                break;
        }
    }


    private void GetBangdan(int type) {
        map.put("type", type);
        map.put("liveId", liveId);
        RequestParams params = NetUtils.sendHttpGet(Url, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    //[{"id":319,"nickName":"用户昵称","contributeJucaiBills":贡献聚财币}]
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.optInt("id", -1);
                            String nickName = object.optString("nickName", "");
                            int contributeJucaiBills = object.optInt("contributeJucaiBills", 0);
                            String userFace = object.optString("userFace", "");
                            Rebate rebate = new Rebate();
                            rebate.setId(id);
                            if (i == 0) {
                                rebate.setLeavel(true);
                            } else {
                                rebate.setLeavel(false);
                            }
                            rebate.setNinkName(nickName);
                            rebate.setUserFace(userFace);
                            rebate.setContributeJucaiBills(contributeJucaiBills);
                            list.add(rebate);
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

    private void init() {
        liveId = getArguments().getInt("liveId", -1);
        button_sun = (RadioButton) view.findViewById(R.id.button_sun);
        button_sun.setChecked(true);
        button_sun.setOnClickListener(this);
        button_month = (RadioButton) view.findViewById(R.id.button_month);
        button_month.setOnClickListener(this);
        lv_news = (RecyclerView) view.findViewById(R.id.lv_news);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        lv_news.setLayoutManager(lm);
        adapter = new NewsAdapter(getActivity(), list);
        lv_news.setAdapter(adapter);

        select(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sun:
                select(1);
                break;
            case R.id.button_month:
                select(2);
                break;
        }
    }
}
