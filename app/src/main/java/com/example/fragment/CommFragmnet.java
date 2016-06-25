package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.VideoCommAdapter;
import com.example.androidnetwork.R;
import com.example.model.Comments;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/16.
 * <p/>
 * 演播室 评论
 */
public class CommFragmnet extends Fragment {
    private String commUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getvideocomms";
    private View view;
    private VideoCommAdapter adapter;
    private ListView comm_lv;
    private Map<String, Object> map = new HashMap<>();
    private int page = 1;
    private int id;
    private List<Comments> list = new ArrayList<>();

    public CommFragmnet(int id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.commfragmnet, container, false);
        init();

        //获取评论信息
        GetComment();

        return view;
    }


    private void GetComment() {
        //http://192.168.1.134:8080/Jucaipen/jucaipen/getvideocomms?
        // fkId=428&typeId=0&page=1&parentId=0
        map.put("fkId",id);
        map.put("typeId",0);
        map.put("page",page);
        map.put("parentId",0);

        RequestParams params = NetUtils.sendHttpGet(commUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                if (result != null) {
                    list = JsonUtil.getcomments(result);
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
        comm_lv = (ListView) view.findViewById(R.id.comm_lv);
        adapter = new VideoCommAdapter(getActivity(), list);
        comm_lv.setAdapter(adapter);

    }
}
