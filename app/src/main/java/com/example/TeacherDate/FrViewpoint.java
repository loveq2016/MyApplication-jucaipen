package com.example.TeacherDate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Activity.HotCareful;
import com.example.adapter.PageIdeaAdapter;
import com.example.androidnetwork.R;
import com.example.model.Opinion;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/14.
 * 他的观点
 */
public class FrViewpoint extends Fragment {
    private String pointUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getideaask";


    private XRecyclerView listview_video;
    private List<Opinion> opinionList = new ArrayList<>();
    private PageIdeaAdapter pageIdeaAdapter;
    private View view;
    private Map<String, Object> map = new HashMap<>();
    private int teacherId;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fr_viewpoint, container, false);
        init();

        return view;
    }

    private void init() {
        teacherId = getArguments().getInt("teacherId");
        GetPoint(page);
        listview_video = (XRecyclerView) view.findViewById(R.id.listview_video);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listview_video.setLayoutManager(manager);

        listview_video.setPullRefreshEnabled(false);
        // listview_video.setOnItemClickListener(this);
        pageIdeaAdapter = new PageIdeaAdapter(getActivity(), opinionList);
        listview_video.setAdapter(pageIdeaAdapter);
        pageIdeaAdapter.setAdapterOnClick(new PageIdeaAdapter.RecyclerViewAdapterOnClick() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent();
                intent.putExtra("id", opinionList.get(position).getId());
                intent.setClass(getActivity(), HotCareful.class);
                startActivity(intent);
            }
        });

        listview_video.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                if (5 >= page) {
                    page++;
                    GetPoint(page);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listview_video.loadMoreComplete();
                    }
                }, 3000);


            }
        });
    }

    private void GetPoint(int pages) {
        map.clear();
        map.put("teacherId", teacherId);
        map.put("typeId", 0);
        map.put("page", pages);
        map.put("isIndex", 1);
        RequestParams params = NetUtils.sendHttpGet(pointUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            //[{"id":690,"title":"CCTV证券实战教学团队2016.3.21收评","desc":"<p>\r\n\t<spa

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            String title = object.getString("title");
                            String desc = object.getString("desc");
                            String insertDate = object.getString("insertDate");
                            Opinion opinion = new Opinion();
                            opinion.setId(id);
                            opinion.setTitle(title);
                            opinion.setDesc(desc);
                            opinion.setInsertdate(insertDate);
                            opinionList.add(opinion);
                        }
                        pageIdeaAdapter.notifyDataSetChanged();
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
