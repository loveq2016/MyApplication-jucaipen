package com.example.AlreadyPay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.adapter.SpecialAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/19.
 * <p/>
 * 已购买的专辑
 */
public class BuySpecial extends Fragment {
    private View view;
    private GridView grd_special;
    private SpecialAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.buyspecial, container, false);

        init();
        return view;
    }

    private void init() {
        grd_special= (GridView) view.findViewById(R.id.grd_special);
        adapter=new SpecialAdapter(getActivity());
        grd_special.setAdapter(adapter);

    }
}
