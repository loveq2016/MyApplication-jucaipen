package com.example.AlreadyPay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.PointAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/19.
 * <p/>
 * 已购买的日志观点
 */
public class BuyLogPoint extends Fragment {
    private View view;
    private ListView lv_point;
    private PointAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.buylogpoint, container, false);

        init();

        return view;
    }

    private void init() {
        lv_point = (ListView) view.findViewById(R.id.lv_point);
        adapter = new PointAdapter(getActivity());
        lv_point.setAdapter(adapter);

    }
}
