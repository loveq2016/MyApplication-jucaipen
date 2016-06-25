package com.example.AlreadyPay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.BuyVideoAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/19.
 * 已购买的视频
 */
public class BuyVideo extends Fragment {
    private View view;
    private BuyVideoAdapter adapter;
    private ListView lv_buyvideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.buyvideo, container, false);

        init();

        return view;
    }

    private void init() {
        lv_buyvideo = (ListView) view.findViewById(R.id.lv_buyvideo);
        adapter = new BuyVideoAdapter(getActivity());
        lv_buyvideo.setAdapter(adapter);
    }
}
