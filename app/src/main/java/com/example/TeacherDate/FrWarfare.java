package com.example.TeacherDate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.WarfareAdapter;
import com.example.androidnetwork.R;
import com.example.model.Warfare;
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
 * Created by Administrator on 2016/6/20.
 * <p/>
 * 战法
 */
public class FrWarfare extends Fragment {
    private View view;
    private ListView lv_warfare;
    private String warfareUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/gettactics";
    private WarfareAdapter adapter;
    private Map<String, Object> map = new ArrayMap<>();
    private List<Warfare> list = new ArrayList<>();
    private int teacherId;
    private int page = 1;

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
        lv_warfare = (ListView) view.findViewById(R.id.lv_warfare);
        adapter = new WarfareAdapter(getActivity(), list);
        lv_warfare.setAdapter(adapter);
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

                if (result != null) {
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
