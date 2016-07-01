package com.example.WritingLive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.PlateAdapter;
import com.example.androidnetwork.R;
import com.example.utils.StringUntils;
import com.example.model.Plate;
import com.example.utils.NetUtils;

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
 * Created by Administrator on 2016/5/27.
 * 浏览解盘
 */
public class ReadingPlate extends Fragment {
    private View view;
    private ListView read_lv;
    private String plateUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/solutiondish";
    private List<Plate> list = new ArrayList<>();
    private Map<String, Object> map = new ArrayMap<>();
    private int id;
    private PlateAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.readingplate, container, false);

        init();
        return view;
    }

    private void init() {
        id = getArguments().getInt("id");
        GetReading();
        read_lv = (ListView) view.findViewById(R.id.read_lv);
        adapter = new PlateAdapter(getActivity(), list);
        read_lv.setAdapter(adapter);
    }

    private void GetReading() {
        list.clear();
        map.clear();
        map.put("liveId", id);
        RequestParams params = NetUtils.sendHttpGet(plateUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            //[{"id":197,"body":"2","insertDate":"2016-06-22 10:15:52.75","isFree":1},

            @Override
            public void onSuccess(String result) {

                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            String body = object.getString("body");
                            String insertDate = object.getString("insertDate");
                            int isFree = object.getInt("isFree");
                            Plate plate = new Plate();
                            plate.setId(id);
                            plate.setBody(body);
                            plate.setInsertDate(insertDate);
                            plate.setIsFree(isFree);
                            list.add(plate);
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
