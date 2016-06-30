package com.example.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
    private XRecyclerView comm_lv;
    private Map<String, Object> map = new HashMap<>();
    private int page = 1;
    private int id;
    private List<Comments> list = new ArrayList<>();


    public CommFragmnet() {
    }

    public CommFragmnet(int id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.commfragmnet, container, false);
        init();

        //获取评论信息
        GetComment(page);

        return view;
    }


    private void GetComment(int pages) {
        map.put("fkId", id);
        map.put("typeId", 0);
        map.put("page", pages);
        map.put("parentId", 0);

        RequestParams params = NetUtils.sendHttpGet(commUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                if (result != null&&result.length()>0) {
                    list = JsonUtil.getcomments(result);
                    adapter.setList(list);
                }
                adapter.notifyDataSetChanged();
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

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        comm_lv = (XRecyclerView) view.findViewById(R.id.comm_lv);
        comm_lv.setLayoutManager(manager);
        adapter = new VideoCommAdapter(getActivity(), list);
        comm_lv.setAdapter(adapter);
        comm_lv.setPullRefreshEnabled(false);
        comm_lv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                if (5 >= page) {
                    page++;
                    GetComment(page);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        comm_lv.loadMoreComplete();
                    }
                }, 3000);
            }
        });

    }
}
