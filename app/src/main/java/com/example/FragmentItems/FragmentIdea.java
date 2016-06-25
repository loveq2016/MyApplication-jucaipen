package com.example.FragmentItems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.Activity.HotCareful;
import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.example.adapter.IdeaAdapter;
import com.example.model.Opinion;
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
 * Created by jucaipen on 16/5/6.
 * <p/>
 * 主页    －－观点模块
 */
public class FragmentIdea extends Fragment implements AdapterView.OnItemClickListener {
    private XRecyclerView ideaListivew;
    private IdeaAdapter adapter;
    private String ideaUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getidealist";
    private View view;
    private Map<String, Object> map = new HashMap<>();
    private List<Opinion> opinionList = new ArrayList<>();
    private int page = 1;
    private Context context = getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context = getContext();

        view = inflater.inflate(R.layout.idea, container, false);

        init();
        Getidea(page);
        return view;
    }

    private void Getidea(int p) {
        map.put("isIndex", 1);
        map.put("page", p);
        RequestParams params = NetUtils.sendHttpGet(ideaUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                opinionList = JsonUtil.getOpinion(result,opinionList);
                adapter.setList(opinionList);
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
        ideaListivew = (XRecyclerView) view.findViewById(R.id.ideaListivew);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ideaListivew.setLayoutManager(manager);

        adapter = new IdeaAdapter(context, opinionList);
        ideaListivew.setAdapter(adapter);
        ideaListivew.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);


        ideaListivew.setPullRefreshEnabled(false);


        ideaListivew.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {


            }

            @Override
            public void onLoadMore() {
                if(opinionList!=null&&opinionList.size()>0){
                    int totlePage= opinionList.get(0).getTotpager();
                    if(totlePage>=page){
                       int p= page++;
                       Getidea(p);
                    }

                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       ideaListivew.loadMoreComplete();
                    }
                },3000);

            }
        });
        //ideaListivew.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (parent.getId()) {
            case R.id.ideaListivew:
                intent.putExtra("id", opinionList.get(position).getId());
                intent.setClass(getActivity(), HotCareful.class);
                startActivity(intent);
                break;
        }

    }
}
