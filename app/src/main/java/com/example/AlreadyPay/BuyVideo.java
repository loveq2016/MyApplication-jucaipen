package com.example.AlreadyPay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.BuyVideoAdapter;
import com.example.androidnetwork.R;
import com.example.utils.NetUtils;
import com.example.utils.StoreUtils;
import com.example.utils.StringUntils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/19.
 * 已购买的视频
 */
public class BuyVideo extends Fragment {
    private View view;
    private BuyVideoAdapter adapter;
    private ListView lv_buyvideo;
    private String BuyUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getmysale";
    private Map<String, Object> maps = new HashMap<>();
    private Map<String, Object> map = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.buyvideo, container, false);
        init();


        maps.put("userId", StoreUtils.getUserInfo(getActivity()));
        maps.put("typeId", 2);
        maps.put("page", 1);
        String str = getDate(BuyUrl, maps);

        Toast.makeText(getActivity(), "=====" + str, Toast.LENGTH_SHORT).show();


        // GetMyBuy();

        return view;
    }

    private void GetMyBuy() {
        RequestParams params = NetUtils.sendHttpGet(BuyUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {

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
        lv_buyvideo = (ListView) view.findViewById(R.id.lv_buyvideo);
        adapter = new BuyVideoAdapter(getActivity());
        lv_buyvideo.setAdapter(adapter);
    }


    public static String getDate(String url, Map<String, Object> map) {
        String[] date = {null};
        RequestParams params = NetUtils.sendHttpGet(url, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    // date[0] = result;

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


        return date[0];
    }
}
