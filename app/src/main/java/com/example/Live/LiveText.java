package com.example.Live;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adapter.TextAdapter;
import com.example.WritingLive.TextVideoLive;
import com.example.androidnetwork.R;
import com.example.model.TextVideo;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8.
 * 文字直播
 */
public class LiveText extends Fragment {
    private XRecyclerView live_lv;
    private View view;
    private String tvurl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getlivelist";
    private TextAdapter adapter;
    private Map<String, Object> map = new HashMap<>();
    private List<TextVideo> list = new ArrayList<>();
    private int page = 1;
    private ProgressBar text_progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.livetext, container, false);

        init();

        return view;
    }

    private void init() {
        GetTvDate(page);
        live_lv = (XRecyclerView) view.findViewById(R.id.live_lv);
        text_progress = (ProgressBar) view.findViewById(R.id.text_progress);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        live_lv.setLayoutManager(manager);
        live_lv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        live_lv.setPullRefreshEnabled(false);
        // live_lv.setOnItemClickListener(this);
        adapter = new TextAdapter(getActivity(), list);
        adapter.setOnItemLinnser(new TextAdapter.OnItemLinnser() {
            @Override
            public void OnClick(View v, int position) {
                Intent intent = new Intent();
                intent.putExtra("teacherId", list.get(position).getTeacherId());
                intent.putExtra("id", list.get(position).getId());
                intent.setClass(getActivity(), TextVideoLive.class);
                getActivity().startActivity(intent);
            }
        });
        live_lv.setAdapter(adapter);
        live_lv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                if (list != null && list.size() > 0) {
                    // int totlePage= list.get(0).getTotpager();
                    if (5 >= page) {
                        int p = page++;
                        GetTvDate(p);
                    }

                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        live_lv.loadMoreComplete();
                    }
                }, 3000);

            }
        });


    }

    private void GetTvDate(int pages) {
        map.clear();
        map.put("liveType", 0);
        map.put("page", pages);
        RequestParams params = NetUtils.sendHttpGet(tvurl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                text_progress.setVisibility(View.GONE);
                live_lv.setVisibility(View.VISIBLE);
                if (result != null) {
                    list = JsonUtil.getTextVideo(result);
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

}
