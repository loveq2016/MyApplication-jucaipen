package com.example.WritingLive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.TvAdapter;
import com.example.androidnetwork.R;
import com.example.model.TextVideo;
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
 * Created by Administrator on 2016/5/27.
 * <p/>
 * 文字直播
 */
public class TextLive extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private ListView text_lv;
    private TvAdapter adapter;
    private String tvUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/gettxtdetails";
    private int id;
    private Map<String, Object> map = new HashMap<>();
    private List<TextVideo> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.textlive, container, false);
        init();
        return view;
    }

    private void init() {
        id = getArguments().getInt("id");
        GettvLive();
        text_lv = (ListView) view.findViewById(R.id.text_lv);
        text_lv.setOnItemClickListener(this);
        adapter = new TvAdapter(getActivity(), list);
        text_lv.setAdapter(adapter);
    }

    private void GettvLive() {
        map.clear();
        map.put("txtId", id);
        RequestParams params = NetUtils.sendHttpGet(tvUrl, map);
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
                            String bodys = object.getString("bodys");
                            int isLock = object.getInt("isLock");
                            String insertDate=object.getString("insertDate");
                            TextVideo video = new TextVideo();
                            video.setId(id);
                            video.setBodys(bodys);
                            video.setInsertDate(insertDate);
                            video.setIsLock(isLock);
                            list.add(video);
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
        //文字直播详情
    }
}
