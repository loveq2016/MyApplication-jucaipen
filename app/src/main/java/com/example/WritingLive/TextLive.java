package com.example.WritingLive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.TvAdapter;
import com.example.androidnetwork.R;
import com.example.model.TextVideo;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.view.TestListView;

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
    private  TextView tv_txtState;
    private TextView tv_txtFans;
    private String tvUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/gettxtdetails";
    private int id;
    private  TextView tv_txtTitle;
    private Map<String, Object> map = new HashMap<>();
    private List<TextVideo> list = new ArrayList<>();
    private String title;
    private int hits;
    private int isEnd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.textlive, container, false);
        init();
        return view;
    }

    private void init() {
        id = getArguments().getInt("id");
        title=getArguments().getString("title");
        hits=getArguments().getInt("hits");
        isEnd=getArguments().getInt("isEnd");
        //在线人数
        tv_txtFans= (TextView) view.findViewById(R.id.tv_txtFans);
        tv_txtTitle= (TextView) view.findViewById(R.id.tv_txtTitle);
        tv_txtState= (TextView) view.findViewById(R.id.tv_txtState);
        tv_txtTitle.setText(title);
        tv_txtFans.setText("在线人数:"+hits);
        if(isEnd==2){
            tv_txtState.setText("正在直播");
        }else {
            tv_txtState.setText("历史直播");
        }
        GettvLive();
        text_lv = (ListView) view.findViewById(R.id.text_lv);
        text_lv.setOnItemClickListener(this);
        adapter = new TvAdapter(getActivity(), list);
        text_lv.setAdapter(adapter);
    }

    private void GettvLive() {
        list.clear();
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
                            String insertDate = object.getString("insertDate");
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
