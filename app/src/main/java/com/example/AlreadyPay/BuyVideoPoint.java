package com.example.AlreadyPay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.AnswerAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/19.
 * 已购买的 直播观点
 */
public class BuyVideoPoint extends Fragment {
    private View view;
    private AnswerAdapter adapter;
    private ListView livepoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.buyvideopoint, container, false);

        init();
        return view;
    }

    private void init() {
        livepoint = (ListView) view.findViewById(R.id.livepoint);
        adapter = new AnswerAdapter(getActivity());
        livepoint.setAdapter(adapter);
    }
}
